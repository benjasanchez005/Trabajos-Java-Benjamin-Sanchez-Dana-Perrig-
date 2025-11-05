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
class NodoAVL {
    Turno turno;
    NodoAVL izq, der;
    int altura;

    NodoAVL(Turno t) {
        this.turno = t;
        this.altura = 1;
    }
}
