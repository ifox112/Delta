package matematyka;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

public class Aplikacja extends JFrame {
	JMenuItem close, save;
	JLabel rownanie, x1;
	JButton oblicz;
	JTextField txta, txtb, txtc;
	int a, b, c;
	double delta, lx0, lx1, lx2;
	java.text.DecimalFormat df=new java.text.DecimalFormat();

	

	public Aplikacja() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		JMenuBar mb = new JMenuBar();
		JMenu plik = new JMenu("Plik");
		close = new JMenuItem("Zamknij");
		save = new JMenuItem("Zapisz");

		plik.add(close);
		plik.add(save);
		mb.add(plik);
		setJMenuBar(mb);
		close.addActionListener(al);
		save.addActionListener(al);

		JLabel title = new JLabel("WPROWADZ DANE ALBY OBLICZYC PIERWIASTYKI RÓWNANIA");
		title.setBounds(15, 5, 400, 30);

		JLabel label1 = new JLabel("A");
		label1.setBounds(90, 30, 30, 20);
		JLabel label2 = new JLabel("B");
		label2.setBounds(170, 30, 30, 20);
		JLabel label3 = new JLabel("C");
		label3.setBounds(250, 30, 30, 20);

		add(title);
		add(label1);
		add(label2);
		add(label3);

		txta = new JTextField("1");
		txta.setBounds(80, 50, 30, 20);
		txtb = new JTextField("2");
		txtb.setBounds(160, 50, 30, 20);
		txtc = new JTextField("1");
		txtc.setBounds(240, 50, 30, 20);

		add(txta);
		add(txtb);
		add(txtc);

		rownanie = new JLabel("(" + txta.getText() + ")x^2 + (" + txtb.getText() + ")x + (" + txtc.getText() + ") = 0");
		rownanie.setBounds(105, 80, 400, 100);
		add(rownanie);

		oblicz = new JButton("OBLICZ");
		oblicz.setBounds(120, 80, 100, 20);
		oblicz.addActionListener(al);
		add(oblicz);
		

		x1 = new JLabel();
		add(x1);
		
		
		
		
		setTitle("PIERWIASTKI RÓWNANIA KWADRATOWEGO");
		setResizable(false);
		setSize(400, 300);
		setVisible(true);
	}

	ActionListener al = new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == close) {
				dispose();
			}

			if (e.getSource() == oblicz) {
				a = Integer.parseInt(txta.getText());
				b = Integer.parseInt(txtb.getText());
				c = Integer.parseInt(txtc.getText());

				Update(a, b, c);
			}

			if (e.getSource() == save) {
				PrintWriter zapis = null;
				try {
					zapis = new PrintWriter("ROWNANIE.txt");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				zapis.println("ROWNANIE:   (" + a + ")x^2 + (" + b + ")x + (" + c + ") = 0");
				if(a!=0) {
				if (delta > 0)
					zapis.println("X1: " + lx1 + "  X2: " + lx2);
				if (delta < 0)
					zapis.println("DELTA UJEMNA");
				if (delta == 0)
					zapis.println("X0: " + lx0);
				}else zapis.println("TO NIE JEST RÓWNANIE KWADRATOWE");
				zapis.close();
			}

		}

		void Update(int a, int b, int c) {
			df.setMaximumFractionDigits(2);
			df.setMinimumFractionDigits(0);
			if (a == 0) {
				x1.setText("TO NIE JEST RÓWNANIE KWADRATOWE");
				x1.setBounds(60, 150, 400, 80);
			}else {
				rownanie.setText("(" + a + ")x^2 + (" + b + ")x + (" + c + ") = 0");
				delta = b * b - 4 * a * c;
				if (delta == 0) {
					lx0 = (-1 * b) / (2 * a);
					x1.setText("X0: " + df.format(lx0));
					x1.setBounds(140, 150, 400, 80);
				} if (delta > 0) {
					lx1 = -1 * (b + Math.sqrt(delta)) / (2 * a);
					lx2 = -1 * (b - Math.sqrt(delta)) / (2 * a);
					x1.setText("X1: " + df.format(lx1) + "   X2: " + df.format(lx2));
					x1.setBounds(115, 150, 400, 80);
				} if (delta < 0) {
					x1.setText("UJEMNA DELTA");
					x1.setBounds(115, 150, 400, 80);
				}
			}
		}
	};

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Aplikacja();
			}
		});
	}
}
