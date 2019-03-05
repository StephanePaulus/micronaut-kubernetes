/*
 * Copyright 2017-2019 original authors
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

package io.micronaut.kubernetes.client.v1;

import io.micronaut.http.annotation.Get;
import io.micronaut.kubernetes.client.v1.endpoints.Endpoints;
import io.micronaut.kubernetes.client.v1.endpoints.EndpointsList;
import io.micronaut.kubernetes.client.v1.services.Service;
import io.micronaut.kubernetes.client.v1.services.ServiceList;
import org.reactivestreams.Publisher;

/**
 * Defines the HTTP requests to query the Kubernetes API.
 *
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.13/">Kubernetes API overview</a>
 *
 * @author Álvaro Sánchez-Mariscal
 * @since 1.0.0
 */
public interface KubernetesOperations {

    String DEFAULT_NAMESPACE = "default";

    /**
     * List or watch objects of kind Service.
     * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.13/#list-all-namespaces-service-v1-core">List all namespaces</a>
     * @return A list of services
     */
    @Get("/services")
    Publisher<ServiceList> listServices();


    /**
     * Read the specified Service.
     * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.13/#read-service-v1-core">Service v1 core - Read operations - Read</a>
     * @param namespace Object name and auth scope, such as for teams and projects
     * @param serviceName The name of the service
     * @return A Service
     */
    @Get("/namespaces/{namespace}/services/{serviceName}")
    Publisher<Service> getService(String namespace, String serviceName);

    /**
     *
     * @param serviceName The name of the service
     * @return Service for the specified name in the namespace ({@value #DEFAULT_NAMESPACE}).
     */
    default Publisher<Service> getService(String serviceName) {
        return getService(DEFAULT_NAMESPACE, serviceName);
    }

    /**
     * List or watch objects of kind Endpoints.
     * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.13/#list-all-namespaces-endpoints-v1-core">Endpoints v1 core - Read Operations - List All NameSpaces</a>
     * @return A list of endpoints
     */
    @Get("/endpoints")
    Publisher<EndpointsList> listEndpoints();

    /**
     * Read the specified Endpoints.
     *
     * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.13/#read-endpoints-v1-core">Endpoints v1 core - Read operations</a>
     * @param namespace object name and auth scope, such as for teams and projects
     * @param serviceName name of the endpoints
     * @return Endpoints
     */
    @Get("/namespaces/{namespace}/endpoints/{serviceName}")
    Publisher<Endpoints> getEndpoints(String namespace, String serviceName);

    /**
     *
     * @param serviceName name of the endpoints
     * @return Returns the endpoints for the specified name in the namespace ({@value #DEFAULT_NAMESPACE}).
     */
    default Publisher<Endpoints> getEndpoints(String serviceName) {
        return getEndpoints(DEFAULT_NAMESPACE, serviceName);
    }

}