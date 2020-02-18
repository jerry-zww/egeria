/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.adminservices.client;

import org.odpi.openmetadata.adminservices.configuration.properties.AccessServiceConfig;
import org.odpi.openmetadata.adminservices.configuration.properties.EnterpriseAccessConfig;
import org.odpi.openmetadata.adminservices.ffdc.exception.OMAGConfigurationErrorException;
import org.odpi.openmetadata.adminservices.ffdc.exception.OMAGInvalidParameterException;
import org.odpi.openmetadata.adminservices.ffdc.exception.OMAGNotAuthorizedException;
import org.odpi.openmetadata.commonservices.ffdc.rest.RegisteredOMAGService;
import org.odpi.openmetadata.commonservices.ffdc.rest.RegisteredOMAGServicesResponse;
import org.odpi.openmetadata.frameworks.connectors.ffdc.InvalidParameterException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MetadataAccessPointConfigurationClient configures a MetadataAccessPoint OMAG Server.  This server
 * can become a cohort member and, through the access services, offers a wide range of specialist APIs
 * and event streams to access and store metadata.
 */
public class MetadataAccessPointConfigurationClient extends CohortMemberConfigurationClient
{
    /**
     * Create a new client with no authentication embedded in the HTTP request.
     *
     * @param adminUserId           administrator's (end user's) userId to associate with calls.
     * @param serverName            name of the server to connect to
     * @param serverPlatformRootURL the network address of the server running the admin services
     * @throws OMAGInvalidParameterException there is a problem creating the client-side components to issue any
     *                                       REST API calls.
     */
    MetadataAccessPointConfigurationClient(String adminUserId,
                                           String serverName,
                                           String serverPlatformRootURL) throws OMAGInvalidParameterException
    {
        super(adminUserId, serverName, serverPlatformRootURL);
    }


    /**
     * Create a new client that passes a connection userId and password in each HTTP request.  This is the
     * userId/password of the calling server.  The end user's userId is passed as the admin userId.
     *
     * @param adminUserId           administrator's (end user's) userId to associate with calls.
     * @param serverName            name of the server to connect to
     * @param serverPlatformRootURL the network address of the server running the admin services
     * @param connectionUserId      caller's system userId embedded in all HTTP requests
     * @param connectionPassword    caller's system password embedded in all HTTP requests
     * @throws OMAGInvalidParameterException there is a problem creating the client-side components to issue any
     *                                       REST API calls.
     */
    MetadataAccessPointConfigurationClient(String adminUserId,
                                           String serverName,
                                           String serverPlatformRootURL,
                                           String connectionUserId,
                                           String connectionPassword) throws OMAGInvalidParameterException
    {
        super(adminUserId, serverName, serverPlatformRootURL, connectionUserId, connectionPassword);
    }


    /*
     * =============================================================
     * Retrieve the current state of the access service configuration
     */


    /**
     * Return the list of access services that are configured for this server.
     *
     * @return list of access service descriptions
     * @throws OMAGNotAuthorizedException the supplied userId is not authorized to issue this command.
     * @throws OMAGInvalidParameterException invalid parameter.
     * @throws OMAGConfigurationErrorException unusual state in the admin server.
     */
    public List<RegisteredOMAGService> getConfiguredAccessServices() throws OMAGNotAuthorizedException,
                                                                            OMAGInvalidParameterException,
                                                                            OMAGConfigurationErrorException
    {
        final String methodName  = "getConfiguredAccessServices";
        final String urlTemplate = "/open-metadata/admin-services/users/{0}/servers/{1}/access-services/configuration";

        RegisteredOMAGServicesResponse restResult = restClient.callRegisteredOMAGServicesGetRESTCall(methodName,
                                                                                                     serverPlatformRootURL + urlTemplate,
                                                                                                     adminUserId,
                                                                                                     serverName);
        return restResult.getServices();
    }


    /*
     * =============================================================
     * Configure server making maximum use of defaults
     */


    /**
     * Enable a single access service.
     *
     * @param serviceURLMarker string indicating which access service it is configuring
     *
     * @throws OMAGNotAuthorizedException the supplied userId is not authorized to issue this command.
     * @throws OMAGInvalidParameterException invalid parameter.
     * @throws OMAGConfigurationErrorException unusual state in the admin server.
     */
    public void configureAccessService(String  serviceURLMarker) throws OMAGNotAuthorizedException,
                                                                        OMAGInvalidParameterException,
                                                                        OMAGConfigurationErrorException
    {
        this.configureAccessService(serviceURLMarker, new HashMap<>());
    }


    /**
     * Enable a single access service.
     *
     * @param serviceURLMarker string indicating which access service it is configuring
     * @param accessServiceOptions property name/value pairs used to configure the access service
     *
     * @throws OMAGNotAuthorizedException the supplied userId is not authorized to issue this command.
     * @throws OMAGInvalidParameterException invalid parameter.
     * @throws OMAGConfigurationErrorException unusual state in the admin server.
     */
    public void configureAccessService(String              serviceURLMarker,
                                       Map<String, Object> accessServiceOptions) throws OMAGNotAuthorizedException,
                                                                                        OMAGInvalidParameterException,
                                                                                        OMAGConfigurationErrorException
    {
        final String methodName    = "configureAccessService";
        final String parameterName = "serviceURLMarker";
        final String urlTemplate   = "/open-metadata/admin-services/users/{0}/servers/{1}/access-services/{2}";

        try
        {
            invalidParameterHandler.validateName(serviceURLMarker, parameterName, methodName);
        }
        catch (InvalidParameterException error)
        {
            throw new OMAGInvalidParameterException(error);
        }

        restClient.callVoidPostRESTCall(methodName,
                                        serverPlatformRootURL + urlTemplate,
                                        accessServiceOptions,
                                        adminUserId,
                                        serverName,
                                        serviceURLMarker);
    }


    /**
     * Enable all access services that are registered into this server.   The configuration properties
     * for each access service can be changed from their default using setAccessServicesConfig operation.
     *
     * @param accessServiceOptions  property name/value pairs used to configure the access services
     *
     * @throws OMAGNotAuthorizedException the supplied userId is not authorized to issue this command.
     * @throws OMAGInvalidParameterException invalid parameter.
     * @throws OMAGConfigurationErrorException unusual state in the admin server.
     */
    public void configureAllAccessServices(Map<String, Object> accessServiceOptions) throws OMAGNotAuthorizedException,
                                                                                            OMAGInvalidParameterException,
                                                                                            OMAGConfigurationErrorException
    {
        final String methodName  = "configureAllAccessServices";
        final String urlTemplate = "/open-metadata/admin-services/users/{0}/servers/{1}/access-services";

        restClient.callVoidPostRESTCall(methodName,
                                        serverPlatformRootURL + urlTemplate,
                                        accessServiceOptions,
                                        adminUserId,
                                        serverName);
    }


    /**
     * Disable the access services.  This removes all configuration for the access services
     * and disables the enterprise repository services.
     *
     * @throws OMAGNotAuthorizedException the supplied userId is not authorized to issue this command.
     * @throws OMAGInvalidParameterException invalid parameter.
     * @throws OMAGConfigurationErrorException unusual state in the admin server.
     */
    public void clearAllAccessServices() throws OMAGNotAuthorizedException,
                                                OMAGInvalidParameterException,
                                                OMAGConfigurationErrorException
    {
        final String methodName  = "clearAllAccessServices";
        final String urlTemplate = "/open-metadata/admin-services/users/{0}/servers/{1}/access-services";

        restClient.callVoidDeleteRESTCall(methodName,
                                          serverPlatformRootURL + urlTemplate,
                                          adminUserId,
                                          serverName);
    }


    /*
     * ===================================================================================
     * Configure access and enterprise services with advanced options overriding defaults
     */

    /**
     * Set up the configuration for all of the open metadata access services (OMASs).  This overrides
     * the current values.
     *
     * @param accessServicesConfig - list of configuration properties for each access service.
     * @throws OMAGNotAuthorizedException the supplied userId is not authorized to issue this command.
     * @throws OMAGInvalidParameterException invalid parameter.
     * @throws OMAGConfigurationErrorException unusual state in the admin server.
     */
    public void setAccessServicesConfig(List<AccessServiceConfig> accessServicesConfig) throws OMAGNotAuthorizedException,
                                                                                               OMAGInvalidParameterException,
                                                                                               OMAGConfigurationErrorException
    {
        final String methodName  = "setAccessServicesConfig";
        final String urlTemplate = "/open-metadata/admin-services/users/{0}/servers/{1}/access-services/configuration";

        restClient.callVoidPostRESTCall(methodName,
                                        serverPlatformRootURL + urlTemplate,
                                        accessServicesConfig,
                                        adminUserId,
                                        serverName);
    }


    /**
     * Set up the configuration that controls the enterprise repository services.  These services are part
     * of the Open Metadata Repository Services (OMRS).  They provide federated queries and federated event
     * notifications that cover metadata from the local repository plus any repositories connected via
     * open metadata repository cohorts.  These services are configured automatically when the access
     * services are enabled and so this call is not normally needed unless the default settings for
     * the enterprise access services are not sufficient.
     *
     * @param enterpriseAccessConfig - enterprise repository services configuration properties.
     * @throws OMAGNotAuthorizedException the supplied userId is not authorized to issue this command.
     * @throws OMAGInvalidParameterException invalid parameter.
     * @throws OMAGConfigurationErrorException unusual state in the admin server.
     */
    public void setEnterpriseAccessConfig(EnterpriseAccessConfig enterpriseAccessConfig) throws OMAGNotAuthorizedException,
                                                                                                OMAGInvalidParameterException,
                                                                                                OMAGConfigurationErrorException
    {
        final String methodName  = "setEnterpriseAccessConfig";
        final String urlTemplate = "/open-metadata/admin-services/users/{0}/servers/{1}/enterprise-services/configuration";

        restClient.callVoidPostRESTCall(methodName,
                                        serverPlatformRootURL + urlTemplate,
                                        enterpriseAccessConfig,
                                        adminUserId,
                                        serverName);
    }
}