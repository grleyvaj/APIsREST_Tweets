package com.example.api.twitter.application.shared;


/**
 * @author Gloria R. Leyva Jerez
 * This class contains the OpenAPI documentation of the APIs of the Exception (ErrorInfo)
 */
public class Constants {

    /***********************
     * ErrorInfo's constants
     **********************/
    public static final String error_schema_description = "Entity that manages the error information that occurs when an exception is thrown.";

    public static final String uri_description = "URI where the exception is throud";
    public static final String uri_example = "/twitter/v1/tweets";

    public static final String method_description = "Method that produces the exception";
    public static final String method_example = "GET | POST | PUT | DELETE | ...";

    public static final String message_description = "Error description";
    public static final String message_example = "Se ha producido un error debido a que ...";

    public static final String httpStatus_description = "HttpStatus of response";
    public static final String httpStatus_example = "Bad Request | Not Found | Method Not Allowed | ConstraintViolationshared.exception | Precondition Failed | Unprocessable Entity | ...";

    public static final String statusCode_description = "Automatic response status code";
    public static final String statusCode_example = "400 | 404 | 405 | 409 | 412 | 422 | ...";

    public static final String errorCode_description = "Error code generated in the system";
    public static final String errorCode_example = "5| 4 | 6 | 3 | 1 | 2 | ...";

    public static final String type_description = "Exception thrown";
    public static final String type_example = "HttpMessageNotReadableshared.exception | ResourceNotFoundException | HttpRequestMethodNotSupportedshared.exception | ConstraintViolationshared.exception | NotValidIDshared.exception | Unprocessable Entity | ...";


    /***********************
     * ExceptionResponseHandler's constants
     **********************/

    public static final String notFound_resp_description = "El recurso solicitado no existe.";
    public static final String notFound_resp_name = "Devuelve un ErrorInfo al producirse una excepción ResourceNotFoundException debido a que el recurso solicitado no existe.";

    public static final String internalError_resp_description = "Error interno del servidor.";
    public static final String internalError_resp_name = "Devuelve un ErrorInfo al producirse una excepción HttpServerErrorException debido a que se ha producido un error interno en el servidor.";

    public static final String incompletePtmError_resp_description = "Ausencia de parámetros que son requeridos para completar la solicitud realizada. Por ejemplo: token, path variables o parámetros de la solicitud.";
    public static final String incompletePtmError_resp_name = "Devuelve un ErrorInfo al producirse una excepción MissingRequestHeaderException debido a que faltan parámetros que son requeridos para completar la solicitud realizada.";
}
