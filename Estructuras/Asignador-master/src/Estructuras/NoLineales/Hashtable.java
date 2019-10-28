/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.NoLineales;

/**
 *
 * @author lizandro
 */
public class Hashtable {
    private MiniHash[] Hash;
    private int M;
    private int Posiciones;
    public Hashtable(){
        M=37;
        Posiciones=0;
        Hash= new MiniHash[M];
    }
        
    private void ReHash(int N){
        int SubM=getM();
        MiniHash[] SubHash=getHash();
        
        setM(this.SiguientePrimo(getM() + N));
        setHash(new MiniHash[getM()]);
        Posiciones=0;
        
        for(int i=0;i<SubM;i++){
            if(SubHash[i]!=null)
                Agregar(SubHash[i].getClave(),SubHash[i].getContenido());
        }
    }
    public boolean Eliminar(int Clave){
        MiniHash ConteHash= getHash()[Clave % getM()];
        if(ConteHash==null)
            return false;
        else{
            if (ConteHash.getClave() != Clave) {
                int Tope = Clave % getM();
                int i = 1;
                while (Tope < getM()) {
                    Tope = Clave % getM() + (Clave % 7 + 1) * i;
                    ConteHash = getHash()[Tope];
                    if (ConteHash == null) {
                    }else if(ConteHash.getClave() == Clave){
                        getHash()[Tope]=null;
                        ReHash(0);
                        return true;
                    }
                    i++;
                }
              return false;
            }else{
                getHash()[Clave % getM()]=null;
                ReHash(0);
                return true;
            }
        }
    }
    
    public Object Buscar(int Clave){
        MiniHash ConteHash= getHash()[Clave % getM()];
        if(ConteHash==null)
            return null;
        else{
            if (ConteHash.getClave() != Clave) {
                int Tope = Clave % getM();
                int i = 1;
                while (Tope < getM()) {
                    Tope = Clave % getM() + (Clave % 7 + 1) * i;
                    //
                    if(Tope>=getM()){
                       return null;
                    }
                    //
                    ConteHash = getHash()[Tope];
                    if (ConteHash == null) {
                    }else if(ConteHash.getClave() == Clave){
                        return ConteHash.getContenido();
                    }
                    i++;
                }
              return null;
            }else{
                return ConteHash.getContenido();
            }
        }
    }
    public boolean Agregar(int Clave,Object Contenido){
        if(((double)Posiciones/getM())*100 >55){
            ReHash(1);
        }
        MiniHash ConteHash= getHash()[Clave % getM()];
        if(ConteHash==null){
            Posiciones++;
            getHash()[Clave % getM()]=new MiniHash(Clave,Contenido);
            return true;
        }else{
            int Tope=Clave%getM();
            int i=1;
           // System.out.println("Esta Clave Ya Fue Agregada");
            while(Tope<getM()){
                Tope=Clave%getM()+(Clave%7+1)*i;
                if(Tope>=getM()){
                   // System.out.println(M);
                    this.ReHash(1);
                   // System.out.println(M);
                    Tope=Clave%getM()+(Clave%7+1)*i;
                }
                ConteHash= getHash()[Tope];
                if(ConteHash==null){
                    Posiciones++;
                    getHash()[Tope]=new MiniHash(Clave,Contenido);
                    return true;
                }
                i++;
            }
            System.out.println("Fuera De Indice");
        }
        return false;
    }
    //Sumar 1
    private int  SiguientePrimo(int Num){
        for(int i=2;i<Num;i++){
            if((Num)%i==0){
                return SiguientePrimo(Num+1);
            }
        }
        return Num;
    }

    /**
     * @return the Hash
     */
    public MiniHash[] getHash() {
        return Hash;
    }

    /**
     * @param Hash the Hash to set
     */
    public void setHash(MiniHash[] Hash) {
        this.Hash = Hash;
    }

    /**
     * @return the M
     */
    public int getM() {
        return M;
    }

    /**
     * @param M the M to set
     */
    public void setM(int M) {
        this.M = M;
    }
    
    
}
