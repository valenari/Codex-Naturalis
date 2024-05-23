package test;

import java.util.ArrayList;
import java.util.List;

import cardsModel.Carta;
/*
 * calcolatore del angole c'è sono nella tavolo
 */
public class ListaDiCatre {
	//ArrayList<Carta> ListaDiCarte = new ArrayList<Carta>();	
	 private List<Carta> carteList;
	 
	 public ListaDiCatre() {
	        this.carteList = new ArrayList<>();
	    }
	 
	 public void addCart(Carta carta) {
		 carteList.add(carta);
	    }

	    public void removeCart(Carta carta) {
	    	carteList.remove(carta);
	    }
	    	    
	    public Carta getCart(int index) {
	        if (index >= 0 && index < carteList.size()) {
	            return carteList.get(index);
	        }
	        return null;
	    }

	    public List<Carta> getAllCart() {
	        return new ArrayList<>(carteList);
	    }

	    public int size() {
	        return carteList.size();
	    }
	    
	    @Override
	    public String toString() {
	        return "CartList{" + "CartList=" + carteList + '}';
	    }
}