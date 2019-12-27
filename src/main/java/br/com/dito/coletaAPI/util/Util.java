package br.com.dito.coletaAPI.util;

import org.springframework.stereotype.Service;

/**
 * @author Elton H. Paula
 */
@Service
public class Util {

    public Boolean isLetter(String nome, int size) {
        Boolean result = null;
        for(int i = 0; i <= size; i++) {
            int a = nome.toCharArray()[0];
            if (!Character.isLetter(nome.toCharArray()[0])){
                result = false;
            }
        }
        if(result == null) {
            result = true;
        }

        return result;
    }

    public Boolean isLetter(String nome) {
        return this.isLetter(nome,2);
    }
}
