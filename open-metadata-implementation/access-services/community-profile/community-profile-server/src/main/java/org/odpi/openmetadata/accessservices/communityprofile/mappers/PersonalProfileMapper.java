/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.accessservices.communityprofile.mappers;

/**
 * PersonalProfileMapper provides property name mapping for Personal Profiles.
 */
public class PersonalProfileMapper
{
    public static final String PERSONAL_PROFILE_TYPE_GUID = "ac406bf8-e53e-49f1-9088-2af28bbbd285";
    public static final String PERSONAL_PROFILE_TYPE_NAME = "Person";

    public static final String QUALIFIED_NAME_PROPERTY_NAME        = "qualifiedName";        /* from Referenceable entity */
    public static final String NAME_PROPERTY_NAME                  = "name";                 /* from ActorProfile entity */
    public static final String DESCRIPTION_PROPERTY_NAME           = "description";          /* from ActorProfile entity */
    public static final String FULL_NAME_PROPERTY_NAME             = "fullName";             /* from Person entity */
    public static final String JOB_TITLE_PROPERTY_NAME             = "jobTitle";             /* from Person entity */
    public static final String ADDITIONAL_PROPERTIES_PROPERTY_NAME = "additionalProperties"; /* from Referenceable entity */

    public static final String PROFILE_IDENTITY_TYPE_GUID = "01664609-e777-4079-b543-6baffe910ff1";
    public static final String PROFILE_IDENTITY_TYPE_NAME = "ProfileIdentity";
    /* end 1 ActorProfile; end 2 UserIdentity */


}
