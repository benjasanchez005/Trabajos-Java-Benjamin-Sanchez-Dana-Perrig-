/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicointegrador;

/**
 *
 * @author Dana
 */

import java.time.LocalDateTime;

public class Quirofano implements Comparable<Quirofano> {
    public int id;
    public LocalDateTime finOcupado;

    public Quirofano(int id) {
        this.id = id;
        this.finOcupado = LocalDateTime.now();
    }

    @Override
    public int compareTo(Quirofano otro) {
        return this.finOcupado.compareTo(otro.finOcupado);
    }
}
