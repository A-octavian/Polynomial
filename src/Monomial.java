import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Monomial {

	private int putere;
	private int coef;
	
	public Monomial(int putere, int coef) {
		this.putere = putere;
		this.coef = coef;
	}

	public int getPutere() {
		return putere;
	}

	public void setPutere(int putere) {
		this.putere = putere;
	}

	public int getCoef() {
		return coef;
	}

	public void setCoef(int coef) {
		this.coef = coef;
	}
	
	public Monomial adunare(Monomial x) {
		Monomial aux = new Monomial(0,0);
		if ( this.putere == x.putere) {
			aux.putere = this.putere;
			aux.coef = this.coef + x.coef;
		}
		return aux;
	}
	
	public Monomial scadere(Monomial x) {
		Monomial aux = new Monomial(0,0);
		if ( this.putere == x.putere) {
			aux.putere = this.putere;
			aux.coef = this.coef - x.coef;
		}
		return aux;
	}

	public Monomial inmultire(Monomial x){
		Monomial aux = new Monomial(0,0);
		aux.setCoef(this.coef * x.coef);
		aux.setPutere(this.putere + x.putere);
		return aux;
	}

	public Monomial derivare(){
		Monomial aux = new Monomial(0,0);
		if(this.getPutere() >= 1){
			aux.setCoef(this.getCoef()*this.getPutere());
			aux.setPutere(this.getPutere()-1);

		}
		else
			aux.setCoef(0);
		return aux;
	}

	public static Comparator<Monomial>comparatorPutere() {
		Comparator<Monomial> comp = new Comparator<Monomial>(){
			@Override
			public int compare(Monomial x1, Monomial x2) {
				return Integer.compare(x2.getPutere(), x1.getPutere());
			}
		};
		return comp;
	}

	public boolean valid ( String monom){
		if(!monom.matches("^[xX0-9\\^\\*\\-]*")) return false;

		String[] aux = new String[5];
		Pattern polyF = Pattern.compile("\\^");
		Matcher m = polyF.matcher(monom);
		String s = new String();
		while(m.find()) {
			s = m.group();
		}
		if(s.isEmpty()){
			aux = monom.split("[a-zA-Z]");
			if(aux.length == 0) {
				putere = 1;
				coef = 1;
			}
			else {
				if(!aux[0].isEmpty()) {
					if (aux[0].equals("-")) coef = -1;
					else coef = Integer.parseInt(aux[0]);
				}
				else coef = 1;
				if(aux[0] == monom)	putere = 0;
				else putere = 1;
			}
		}	else {
			aux = monom.split("\\^");
			try {
				String nr = new String();
				for( int i = 0; i < aux[0].length(); i++){
					char c = aux[0].charAt(i);
					if(c == 45 ) nr += c;
					if( c > 47 && c < 58) nr += c;
				}
				if( nr.isEmpty()) coef = 1;
				else {
					if (nr.equals("-")) coef = -1;
					else coef = Integer.parseInt(nr);
				}
				putere = Integer.parseInt(aux[1]);
			} catch (NumberFormatException e){
				System.out.println("Format nevalid");
			}
		}
		return true;
	}
}
