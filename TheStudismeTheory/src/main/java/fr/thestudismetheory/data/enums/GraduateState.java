/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.enums;

/**
 * Liste des états possible de "Graduate"
 * Doivent être des constantes int négatives !
 * @author vincent
 */
final public class GraduateState {
    final static public int GRADUATE_IN_PROGRESS = -1;
    final static public int GRADUATE_FIRE        = -2;
    final static public int GRADUATE_NOT_INSCR   = -3;

    //Private pour empécher l'instantion
    private GraduateState() {}
    
}
