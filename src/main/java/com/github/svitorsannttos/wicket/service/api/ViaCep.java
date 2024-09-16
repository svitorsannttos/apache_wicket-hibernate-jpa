package com.github.svitorsannttos.wicket.service.api;

import com.github.svitorsannttos.wicket.model.dto.ZipCodeDTO;
import com.github.svitorsannttos.wicket.util.Util;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

public class ViaCep implements Serializable {

    private static final long serialVersionUID = 1L;

    static String webService = "https://viacep.com.br/ws/";
    static int codeSucesso = 200;

    public static ZipCodeDTO findZipCode(String zipCode) throws Exception {
        String urlViaCep = webService + zipCode + "/json";

        try {
            URL url = new URL(urlViaCep);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection.getResponseCode() != codeSucesso)
                throw new RuntimeException("HTTP error code : " + connection.getResponseCode());

            BufferedReader response = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            String jsonEmString = Util.convertJsonToString(response);

            Gson gson = new Gson();

            return gson.fromJson(jsonEmString, ZipCodeDTO.class);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
