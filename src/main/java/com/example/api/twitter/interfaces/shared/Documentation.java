package com.example.api.twitter.interfaces.shared;

/**
 * @author Gloria R. Leyva Jerez
 * This class contains the OpenAPI documentation to VRP API
 */
public class Documentation {

    public static final String get_tweets_op_summary = "Consultar tweets";
    public static final String get_tweets_op_description = "Consultar tweets previamente almacenados.";
    public static final String get_tweets_op_resp_description = "Retorna el listado de tweets que corresponsen con las reglas: N seguidires e idiomas permitidos.";

    public static final String get_tweet_by_id_op_summary = "Consultar tweet por su identificador";
    public static final String get_tweet_by_id_op_description = "Consultar tweet por su identificador.";
    public static final String get_tweet_by_id_op_resp_description = "Retorna el tweet correspondiente al identificador especificado.";

    public static final String validate_tweet_by_id_op_summary = "Marcar un tweet como validado";
    public static final String validate_tweet_by_id_op_description = "Validar tweet. Cuando se almacene un tweet por default no se valida y precisamente mediante esta opción el campo 'validation' del tweet es actualizado a TRUE. ";
    public static final String validate_tweet_by_id_op_resp_description = "Retorna el tweet correspondiente al identificador especificado.";

    public static final String get_validate_tweet_by_user_op_summary = "Consultar los tweets validados por usuario.";
    public static final String get_validate_tweet_by_user_op_description = "Consultar los tweets por el identificador del usuario que previamentes han sido validados. ";
    public static final String get_validate_tweet_by_user_op_resp_description = "Retorna el tweet correspondiente al identificador especificado.";

    public static final String get_hashtags_op_summary = "Consultar una clasificación de los N hashtags más usados";
    public static final String get_hashtags_op_description = "Consultar una clasificación de los N hashtags más usados (default 10). ";
    public static final String get_hashtags_op_resp_description = "Retorna el listado de los N hashtags más usados.";
    public static final String get_hashtags_ptm_description = "Clasificación de N hashtags";

    public static final String pageable_description = "Parámetro para indicar la paginación";
    public static final String pageable_request = "{"
            + "\"page\": 0,"
            + "\"size\": 5,"
            + "\"sort\": \"id,asc\""
            + "}";
}
