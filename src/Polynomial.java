import java.util.*;

public class Polynomial {
	
private ArrayList<Monomial> poli;

public Polynomial(ArrayList<Monomial> poli) {
	this.poli = poli;
}


public ArrayList<Monomial> getPoli() {
	return poli;
}


public void setPoli(ArrayList<Monomial> poli) {
	this.poli = poli;
}


public Polynomial adunare (Polynomial x) {
	ArrayList<Monomial> poli = new ArrayList<Monomial>();
	Monomial aux1 = new Monomial(0,0);
	ListIterator<Monomial> i =  this.poli.listIterator();
	ListIterator<Monomial> j = x.poli.listIterator();
	while(i.hasNext() || j.hasNext()){
		if (!i.hasNext()){
			poli.add(j.next());
			break;
		}
		else if (!j.hasNext()){
			poli.add(i.next());
			break;
		} else {
			Monomial a = i.next();
			Monomial b = j.next();
			if (a.getPutere() == b.getPutere()) {
				aux1 = a.adunare(b);
				if(aux1.getCoef() != 0)
					poli.add(aux1);
			} else if (a.getPutere() > b.getPutere()) {
				aux1 = a;
				poli.add(aux1);
				j.previous();
			} else if (a.getPutere() < b.getPutere()) {
				aux1 = b;
				poli.add(aux1);
				i.previous();
			}
		}
	}

	Polynomial aux = new Polynomial(poli);
	return aux;
}


public Polynomial scadere (Polynomial x)  {
	ArrayList<Monomial> poli = new ArrayList<Monomial>();
	Monomial aux1 = new Monomial(0,0);
	ListIterator<Monomial> i =  this.poli.listIterator();
	ListIterator<Monomial> j = x.poli.listIterator();
	while(i.hasNext() || j.hasNext()){
		if (!i.hasNext()){
			Monomial a = j.next();
			a.setCoef(-a.getCoef());
			poli.add(a);
			break;
		}
		else if (!j.hasNext()){
			poli.add(i.next());
			break;
		} else {
			Monomial a = i.next();
			Monomial b = j.next();
			if (a.getPutere() == b.getPutere()) {
				aux1 = a.scadere(b);
				if(aux1.getCoef() != 0)
					poli.add(aux1);
			} else if (a.getPutere() > b.getPutere()) {
				aux1 = a;
				poli.add(aux1);
				j.previous();
			} else if (a.getPutere() < b.getPutere()) {
				aux1 = b;
				aux1.setCoef(-aux1.getCoef());
				poli.add(aux1);
				i.previous();
			}
		}
	}
	Polynomial aux = new Polynomial(poli);
	return aux;
}

public Polynomial inmultire ( Polynomial x){
	ArrayList<Monomial> poli1 = new ArrayList<Monomial>();
	Monomial aux1 = new Monomial(0,0);
	for( Monomial i : this.getPoli())
		for(Monomial j : x.getPoli()){
			aux1=i.inmultire(j);
			for(Monomial k: poli1) {
				if (k.getPutere() == aux1.getPutere()) {
					aux1 = aux1.adunare(k);
					k.setPutere(-1);
					k.setCoef(0);
				}
			}
				poli1.add(aux1);
		}

	Polynomial aux = new Polynomial(poli1);
	Collections.sort(aux.getPoli(),Monomial.comparatorPutere());
	return aux;
}

public Polynomial derivare(){
	ArrayList<Monomial> poli1 = new ArrayList<Monomial>();
	for(Monomial i: this.getPoli())
		poli1.add(i.derivare());

	Polynomial aux = new Polynomial(poli1);
	return aux;
}

public String afisare(){
	String rezultat = new String();
	for( Monomial i : this.getPoli()){
		if(i.getCoef() == 1){
			if (i.getPutere() == 0)
				rezultat = rezultat + "+" + i.getCoef();
			else if (i.getPutere() == 1) rezultat = rezultat  + "+x";
			else rezultat = rezultat + "+" + "x^" + i.getPutere();
		}
		else if(i.getCoef() == -1){
			if (i.getPutere() == 0)
				rezultat = rezultat + i.getCoef();
			else if (i.getPutere() == 1) rezultat = rezultat  + "-x";
			else rezultat = rezultat + "-x^" + i.getPutere();
		}
		else if(i.getCoef() < 0) {
			if (i.getPutere() == 0)
				rezultat = rezultat + i.getCoef();
			else if (i.getPutere() == 1) rezultat = rezultat + i.getCoef() + "x";
			else rezultat = rezultat + i.getCoef() + "x^" + i.getPutere();
		}
		else if(i.getCoef()  > 0) {
			if (i.getPutere() == 0)
				rezultat = rezultat + "+" + i.getCoef();
			else if (i.getPutere() == 1) rezultat = rezultat + "+" + i.getCoef() + "x";
			else rezultat = rezultat + "+"  + i.getCoef() + "x^" + i.getPutere();
		}
	}
	rezultat = (rezultat.charAt(0) == '+') ? rezultat.substring(1) : rezultat;
	return rezultat;
}

public boolean stringToPoli(String s) {
		s = (s.contains("-")) ? s.replace("-","+-") : s;
		try{
			s = (s.charAt(0) == '+') ? s.substring(1) : s;
		}
		catch (StringIndexOutOfBoundsException e){
			return false;
		}
		String[] monoame = s.split("\\+");
		for(String i : monoame){
			Monomial monom = new Monomial(0,0);
			if(!monom.valid(i))
				return false;
			poli.add(monom);
		}
	return true;
	}
}