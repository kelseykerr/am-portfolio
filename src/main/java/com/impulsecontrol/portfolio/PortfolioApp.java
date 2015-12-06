package com.impulsecontrol.portfolio;

import com.impulsecontrol.portfolio.auth.ExampleAuthorizer;
import com.impulsecontrol.portfolio.core.*;
import com.impulsecontrol.portfolio.db.ArtworkDAO;
import io.dropwizard.auth.AuthValueFactoryProvider;
import com.impulsecontrol.portfolio.auth.ExampleAuthenticator;
import com.impulsecontrol.portfolio.filter.DateRequiredFeature;
import com.impulsecontrol.portfolio.resources.PhotographyResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class PortfolioApp extends Application<PortfolioConfiguration> {
    public static void main(String[] args) throws Exception {
        new PortfolioApp().run(args);
    }

    private final HibernateBundle<PortfolioConfiguration> hibernateBundle =
            new HibernateBundle<PortfolioConfiguration>(Artwork.class, Category.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(PortfolioConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    @Override
    public String getName() {
        return "Ann Marie's Portfolio";
    }

    @Override
    public void initialize(Bootstrap<PortfolioConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );
        bootstrap.addBundle(new AssetsBundle());
        bootstrap.addBundle(new MigrationsBundle<PortfolioConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(PortfolioConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
    }

    @Override
    public void run(PortfolioConfiguration configuration, Environment environment) {
        final ArtworkDAO artworkDAO = new ArtworkDAO(hibernateBundle.getSessionFactory());

        environment.jersey().register(DateRequiredFeature.class);
        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new ExampleAuthenticator())
                .setAuthorizer(new ExampleAuthorizer())
                .setRealm("SUPER SECRET STUFF")
                .buildAuthFilter()));
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new PhotographyResource(artworkDAO));
    }
}
