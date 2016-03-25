/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.enums;

import java.util.HashSet;
import java.util.Set;

/**
 * Les défauts d'un étudiant
 *
 * @author vincent
 */
public enum StudentFlaw {
    ;
        
    /**
     * Analyse un bitset venant de la BD vers un set de StudentFlaw
     * @see StudentFlaw#flawsToBitset(java.util.Set) 
     * @param bitset
     * @return Le Set de StudentFlaw correspondant au bitset
     */
    static public Set<StudentFlaw> parseFlaws(int bitset){
        Set<StudentFlaw> flaws = new HashSet<>();
        
        for(StudentFlaw flaw : values()){
            int mask = 1 << flaw.ordinal();
            
            if((bitset & mask) == mask)
                flaws.add(flaw);
        }
        
        return flaws;
    }
    
    /**
     * Transforme un set de StudentFlaw en bitset (operation inverse de parseFlaws())
     * @see StudentFlaw#parseFlaws(int) 
     * @param flaws
     * @return Le bitset correspondant au set de StudentFlaw
     */
    static public int flawsToBitset(Set<StudentFlaw> flaws){
        int bitset = 0;
        
        for(StudentFlaw flaw : flaws){
            bitset |= (1 << flaw.ordinal());
        }
        
        return bitset;
    }
}
