/*
 * Copyright 2017 original authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package org.particleframework.web.router;

import org.particleframework.http.HttpMethod;
import org.particleframework.http.HttpRequest;
import org.particleframework.http.MediaType;
import org.particleframework.http.uri.UriMatcher;

import java.net.URI;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Represents a {@link Route} that matches a {@link URI}
 *
 * @author Graeme Rocher
 * @since 1.0
 */
public interface UriRoute extends Route, UriMatcher {

    /**
     * Defines routes nested within this route
     *
     * @param nested The nested routes
     * @return This route
     */
    UriRoute nest(Runnable nested);

    /**
     * @return The HTTP method for this route
     */
    HttpMethod getHttpMethod();
    /**
     * Match this route within the given URI and produce a {@link RouteMatch} if a match is found
     *
     * @param uri The URI The URI
     * @return An {@link Optional} of {@link RouteMatch}
     */
    @Override
    default Optional<UriRouteMatch> match(URI uri) {
        return match(uri.toString());
    }

    /**
     * Match this route within the given URI and produce a {@link RouteMatch} if a match is found
     *
     * @param uri The URI The URI
     * @return An {@link Optional} of {@link RouteMatch}
     */
    @Override
    Optional<UriRouteMatch> match(String uri);

    @Override
    UriRoute accept(MediaType... mediaType);

    @Override
    UriRoute acceptAll();

    @Override
    UriRoute where(Predicate<HttpRequest> condition);

    @Override
    UriRoute body(String argument);
}
