package cz.jm.coder;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgressContainer extends PostgreSQLContainer<PostgressContainer> {

        private static final String IMAGE_VERSION = "postgres:11.1";
        private static PostgressContainer container;

        private PostgressContainer() {
            super(IMAGE_VERSION);
        }

        public static PostgressContainer getInstance() {
            if (container == null) {
                container = new PostgressContainer();
            }
            return container;
        }

        @Override
        public void start() {
            super.start();
            System.setProperty("DB_URL", container.getJdbcUrl());
            System.setProperty("DB_USERNAME", container.getUsername());
            System.setProperty("DB_PASSWORD", container.getPassword());
        }

        @Override
        public void stop() {
            //do nothing, JVM handles shut down
        }

}
