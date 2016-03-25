/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.thestudismetheory.data.enums;

import java.util.HashSet;
import java.util.Set;

/**
 * Les défaut d'un étudiant
 *
 * @author vincent
 */
public enum StudentFlaw {
    ;
        
    static public Set<StudentFlaw> parseFlaws(int bitset){
        Set<StudentFlaw> flaws = new HashSet<>();
        
        for(StudentFlaw flaw : values()){
            int mask = 1 << flaw.ordinal();
            
            if((bitset & mask) == mask)
                flaws.add(flaw);
        }
        
        return flaws;
    }
    
    static public int flawsToBitset(Set<StudentFlaw> flaws){
        int bitset = 0;
        
        for(StudentFlaw flaw : flaws){
            bitset |= (1 << flaw.ordinal());
        }
        
        return bitset;
    }
}
