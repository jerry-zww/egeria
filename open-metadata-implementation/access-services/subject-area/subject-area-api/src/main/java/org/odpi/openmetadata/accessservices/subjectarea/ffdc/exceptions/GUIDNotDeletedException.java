/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.accessservices.subjectarea.ffdc.exceptions;

/**
 * The GUIDNotDeletedException is thrown by the Subject Area OMAS when a unique identifier (guid)
 * used to restore was not deleted. It is only possible to restore from the deleted state i.e. soft deleted.  
 */
public class GUIDNotDeletedException extends SubjectAreaCheckedExceptionBase {
    private String guid=null;
    /**
     * This is the typical constructor used for creating a GUIDNotDeletedException.
     *
     * @param httpCode           http response code to use if this exception flows over a rest call
     * @param className          name of class reporting error
     * @param actionDescription  description of function it was performing when error detected
     * @param errorMessage       description of error
     * @param systemAction       actions of the system as a result of the error
     * @param userAction         instructions for correcting the error
     * @param guid               guid of the entity that was not purged
     */
    public GUIDNotDeletedException(int httpCode, String className, String actionDescription, String errorMessage, String systemAction, String userAction, String guid) {
        super(httpCode, className, actionDescription, errorMessage, systemAction, userAction);
        this.guid=guid;
    }


    /**
     * This is the constructor used for creating a GUIDNotDeletedException that resulted from a previous error.
     *
     * @param httpCode           http response code to use if this exception flows over a rest call
     * @param className          name of class reporting error
     * @param actionDescription  description of function it was performing when error detected
     * @param errorMessage       description of error
     * @param systemAction       actions of the system as a result of the error
     * @param userAction         instructions for correcting the error
     * @param caughtError        the error that resulted in this exception.
     * @param guid               guid of the entity that was not purged
     */
    public GUIDNotDeletedException(int httpCode, String className, String actionDescription, String errorMessage, String systemAction, String userAction, String guid, Throwable caughtError) {
        super(httpCode, className, actionDescription, errorMessage, systemAction, userAction, caughtError);
        this.guid=guid;
    }

    public String getGuid() {
        return guid;
    }

}
