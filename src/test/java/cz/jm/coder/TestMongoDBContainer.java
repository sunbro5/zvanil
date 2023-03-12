package cz.jm.coder;

import org.testcontainers.containers.MongoDBContainer;

public class TestMongoDBContainer extends MongoDBContainer {

        private static final String IMAGE_VERSION = "mongo:4.0.10";
        private static TestMongoDBContainer container;

        private TestMongoDBContainer() {
            super(IMAGE_VERSION);
        }

        public static TestMongoDBContainer getInstance() {
            if (container == null) {
                container = new TestMongoDBContainer();
            }
            container.start();
            return container;
        }

        @Override
        public void start() {
            super.start();
        }

        @Override
        public void stop() {
            //do nothing, JVM handles shut down
        }

}
