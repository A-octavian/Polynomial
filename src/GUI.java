import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

public class GUI extends JFrame implements ActionListener {
    private JTextField jcomp1;
    private JComboBox jcomp2;
    private JTextField jcomp3;
    private JLabel jcomp4;
    private JLabel jcomp5;
    private JButton jcomp6;
    private JLabel jcomp7;
    private JLabel jcomp8;

    public GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        String[] jcomp2Items = {"+", "-", "*","'"};


        jcomp1 = new JTextField();
        jcomp2 = new JComboBox(jcomp2Items);
        jcomp2.addActionListener(this);
        jcomp3 = new JTextField();
        jcomp4 = new JLabel("Polinom 1");
        jcomp5 = new JLabel("Polinom2");
        jcomp6 = new JButton("Calculate");
        jcomp6.addActionListener(this);
        jcomp7 = new JLabel("Result");
        jcomp8 = new JLabel("");

        setPreferredSize(new Dimension(392, 376));
        setLayout(null);

        add(jcomp1);
        add(jcomp2);
        add(jcomp3);
        add(jcomp4);
        add(jcomp5);
        add(jcomp6);
        add(jcomp7);
        add(jcomp8);

        jcomp1.setBounds(120, 35, 135, 30);
        jcomp2.setBounds(140, 120, 100, 25);
        jcomp3.setBounds(120, 80, 135, 30);
        jcomp4.setBounds(55, 35, 65, 25);
        jcomp5.setBounds(55, 80, 65, 25);
        jcomp6.setBounds(140, 165, 100, 25);
        jcomp7.setBounds(65, 225, 60, 25);
        jcomp8.setBounds(120, 225, 300, 25);
        pack();
        setVisible(true);
    }

    private JLabel getEmptyLabel(Dimension d) {
        JLabel label = new JLabel();
        label.setPreferredSize(d);
        return label;
    }

    public void dateIncorecte(){
        JFrame frameMesaj = new JFrame();
        JPanel grid = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.CENTER;
        grid.add(getEmptyLabel(new Dimension(300,80)), constraints);
        constraints.gridy = 0;
        constraints.gridx = 0;

        JLabel mesaj = new JLabel("Forma polinomului este incorecta. Introduceti un nou polinom");
        grid.add(mesaj,constraints);
        ++constraints.gridy;
        JButton butonClose = new JButton("OK");
        butonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMesaj.dispatchEvent(new WindowEvent(frameMesaj, WindowEvent.WINDOW_CLOSING));
            }
        });
        grid.add(butonClose,constraints);

        frameMesaj.setContentPane(grid);
        frameMesaj.pack();
        frameMesaj.setLocationRelativeTo(null);
        frameMesaj.setVisible(true);
        String s = new String();
        jcomp1.setText(s);
        jcomp3.setText(s);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jcomp6) {
            ArrayList<Monomial> poli1 = new ArrayList<Monomial>();
            Polynomial p1 = new Polynomial(poli1);
            ArrayList<Monomial> poli2 = new ArrayList<Monomial>();
            Polynomial p2 = new Polynomial(poli2);
            if(!p1.stringToPoli(jcomp1.getText()))
                dateIncorecte();
            if(!p2.stringToPoli(jcomp3.getText()))
                dateIncorecte();

            String s = (String) jcomp2.getSelectedItem();
            if (s.equals("+")) {
                ArrayList<Monomial> poli = new ArrayList<Monomial>();
                Polynomial result = new Polynomial(poli1);
                result = p1.adunare(p2);
                String rezultat = result.afisare();
                jcomp8.setText(rezultat);
            }
            if (s.equals("-")) {
                ArrayList<Monomial> poli = new ArrayList<Monomial>();
                Polynomial result = new Polynomial(poli1);
                result = p1.scadere(p2);
                String rezultat = result.afisare();
                jcomp8.setText(rezultat);
            }
            if (s.equals("*")) {
                ArrayList<Monomial> poli = new ArrayList<Monomial>();
                Polynomial result = new Polynomial(poli1);
                result = p1.inmultire(p2);
                String rezultat = result.afisare();
                jcomp8.setText(rezultat);
            }
            if (s.equals("'")) {
                ArrayList<Monomial> poli = new ArrayList<Monomial>();
                Polynomial result = new Polynomial(poli1);
                result = p1.derivare();
                String rezultat = result.afisare();
                jcomp8.setText(rezultat);
            }
        }
    }
}