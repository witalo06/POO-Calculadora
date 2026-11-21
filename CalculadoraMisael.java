package packageQualquerProjeto2;
    //Aluno: Misael Witalo Ferreira da Silva
	//RA: 0025369
	import java.awt.EventQueue;
	import javax.swing.JFrame;
	import java.awt.Color;
	import javax.swing.JTextField;
	import javax.swing.SwingConstants;
	import java.awt.Font;
	import javax.swing.JButton;
	import java.awt.event.ActionListener;
	import java.awt.event.ActionEvent;
	
	public class CalculadoraMisael {
		private JFrame frmMisaelSilva;
		private JTextField JTFResultado;
		
		
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						CalculadoraMisael window = new CalculadoraMisael();
						window.frmMisaelSilva.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

/**
 * Create the application.
 */
public CalculadoraMisael() {
	initialize();
}

double valor1, valor2;          // primeiro e segundo numero da conta
String op = "";                 // operacao escolhida: soma, subtracao, multiplicacao, divisao
boolean novoNumero = true;      // true = o proximo digito comeca um numero novo no visor

// Adiciona um digito ao numero que esta no visor
private void digitar(String digito) {
	if (novoNumero || JTFResultado.getText().equals("0")) {
		
		JTFResultado.setText(digito);
		novoNumero = false;
	} else {
		JTFResultado.setText(JTFResultado.getText() + digito);
	}
}

// Coloca a virgula (so uma por numero)
private void virgula() {
	if (novoNumero) {
		JTFResultado.setText("0,");
		novoNumero = false;
	} else if (!JTFResultado.getText().contains(",")) {
		JTFResultado.setText(JTFResultado.getText() + ",");
	}
}

// Lê o numero do visor (troca a virgula por ponto, que e o que o Java entende)
private double lerVisor() {
	return Double.parseDouble(JTFResultado.getText().replace(",", "."));
}

// Mostra o resultado no visor (sem ",0" quando o numero e inteiro)
private void mostrar(double valor) {
	if (valor == Math.rint(valor) && Math.abs(valor) < 1e15) {
		JTFResultado.setText(String.valueOf((long) valor));
	} else {
		JTFResultado.setText(String.valueOf(valor).replace(".", ","));
	}
}

// ===== METODOS DAS OPERACOES =====

// 1. Adicao
public double adicao(double a, double b) {
	return a + b;
}

// 2. Subtracao
public double subtracao(double a, double b) {
	return a - b;
}

// 3. Multiplicacao
public double multiplicacao(double a, double b) {
	return a * b;
}

// 4. Divisao
public double divisao(double a, double b) {
	return a / b;
}

// 5. Potencia (a elevado a b)
public double potencia(double a, double b) {
	return Math.pow(a, b);
}

// Mostra "Erro" no visor e cancela a conta
private void erro() {
	JTFResultado.setText("Erro");
	op = "";
	novoNumero = true;
}

// Faz a conta que estava pendente. Retorna false se deu erro
private boolean calcular() {
	valor2 = lerVisor();

	if (op.equals("soma")) {
		valor1 = adicao(valor1, valor2);
	} else if (op.equals("subtracao")) {
		valor1 = subtracao(valor1, valor2);
	} else if (op.equals("multiplicacao")) {
		valor1 = multiplicacao(valor1, valor2);
	} else if (op.equals("divisao")) {
		if (valor2 == 0) {      // nao existe divisao por zero
			erro();
			return false;
		}
		valor1 = divisao(valor1, valor2);
	} else if (op.equals("potencia")) {
		valor1 = potencia(valor1, valor2);
	}

	// Ex: 0 elevado a -1, ou raiz de numero negativo
	if (Double.isNaN(valor1) || Double.isInfinite(valor1)) {
		erro();
		return false;
	}

	mostrar(valor1);
	return true;
}

// Chamado quando aperta + - * /
private void operacao(String novaOp) {
	if (JTFResultado.getText().equals("Erro")) {
		return;
	}
	if (!op.isEmpty() && !novoNumero) {
		// Ja tinha uma conta pendente (ex: 2 + 3 *): resolve ela primeiro
		if (!calcular()) {
			return;
		}
	} else {
		valor1 = lerVisor();
	}
	op = novaOp;
	novoNumero = true;
}

// Chamado quando aperta =
private void igual() {
	if (op.isEmpty() || JTFResultado.getText().equals("Erro")) {
		return;
	}
	if (calcular()) {
		op = "";
	}
	novoNumero = true;
}

// Chamado quando aperta c: zera tudo
private void limpar() {
	JTFResultado.setText("0");
	valor1 = 0;
	valor2 = 0;
	op = "";
	novoNumero = true;
}

		/**
		 * Initialize the contents of the frame.
		 */
		private void initialize() {
			frmMisaelSilva = new JFrame();
			frmMisaelSilva.setTitle("Misael Witalo F. Silva RA - 0025369");
			// Cor pelo aniversario 26/11/2006: dia 1+26=126, mes 1+11=111, ano 2006 sem um 0=206
			frmMisaelSilva.getContentPane().setBackground(new Color(126, 111, 206));
			frmMisaelSilva.getContentPane().setLayout(null);

			JTFResultado = new JTextField();
			JTFResultado.setText("0");
			JTFResultado.setEditable(false);
			JTFResultado.setHorizontalAlignment(SwingConstants.RIGHT);
			JTFResultado.setFont(new Font("Arial Black", Font.PLAIN, 50));
			JTFResultado.setBounds(10, 30, 439, 73);
			frmMisaelSilva.getContentPane().add(JTFResultado);
			JTFResultado.setColumns(10);

			JButton btnNewButton = new JButton("0");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					digitar("0");
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 50));
			btnNewButton.setBounds(104, 427, 72, 64);
			frmMisaelSilva.getContentPane().add(btnNewButton);

			JButton btnNewButton_1 = new JButton("1");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					digitar("1");
				}
			});
			btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 50));
			btnNewButton_1.setBounds(10, 341, 72, 64);
			frmMisaelSilva.getContentPane().add(btnNewButton_1);

			JButton btnNewButton_3 = new JButton("2");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					digitar("2");
				}
			});
			btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 50));
			btnNewButton_3.setBounds(104, 341, 72, 64);
			frmMisaelSilva.getContentPane().add(btnNewButton_3);

			JButton btnNewButton_4 = new JButton("3");
			btnNewButton_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					digitar("3");
				}
			});
			btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 50));
			btnNewButton_4.setBounds(201, 341, 72, 64);
			frmMisaelSilva.getContentPane().add(btnNewButton_4);
	
			
			JButton btnNewButton_1_1 = new JButton("7");
			btnNewButton_1_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					digitar("7");
				}
			});
			btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 50));
			btnNewButton_1_1.setBounds(10, 193, 72, 64);
			frmMisaelSilva.getContentPane().add(btnNewButton_1_1);
			
			JButton btnNewButton_3_1 = new JButton("8");
			btnNewButton_3_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					digitar("8");
				}
			});
			btnNewButton_3_1.setFont(new Font("Tahoma", Font.PLAIN, 50));
			btnNewButton_3_1.setBounds(104, 193, 72, 64);
			frmMisaelSilva.getContentPane().add(btnNewButton_3_1);
			
			JButton btnNewButton_4_1 = new JButton("9");
			btnNewButton_4_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					digitar("9");	
				}
			});
			btnNewButton_4_1.setFont(new Font("Tahoma", Font.PLAIN, 50));
			btnNewButton_4_1.setBounds(201, 193, 72, 64);
			frmMisaelSilva.getContentPane().add(btnNewButton_4_1);

			JButton btnNewButton_3_2 = new JButton("5");
			btnNewButton_3_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					digitar("5");
				}
			});
			btnNewButton_3_2.setFont(new Font("Tahoma", Font.PLAIN, 50));
			btnNewButton_3_2.setBounds(104, 268, 72, 64);
			frmMisaelSilva.getContentPane().add(btnNewButton_3_2);

			JButton btnNewButton_1_2 = new JButton("4");
			btnNewButton_1_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					digitar("4");
				}
			});
			btnNewButton_1_2.setFont(new Font("Tahoma", Font.PLAIN, 50));
			btnNewButton_1_2.setBounds(10, 268, 72, 64);
			frmMisaelSilva.getContentPane().add(btnNewButton_1_2);

			JButton btnNewButton_4_2 = new JButton("6");
			btnNewButton_4_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					digitar("6");
				}
			});
			btnNewButton_4_2.setFont(new Font("Tahoma", Font.PLAIN, 50));
			btnNewButton_4_2.setBounds(201, 268, 72, 64);
			frmMisaelSilva.getContentPane().add(btnNewButton_4_2);

			JButton btnC = new JButton("c");
			btnC.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					limpar();
				}
			});
			btnC.setFont(new Font("Tahoma", Font.PLAIN, 50));
			btnC.setBounds(10, 114, 72, 64);
			frmMisaelSilva.getContentPane().add(btnC);

			JButton btnSoma = new JButton("+");
			btnSoma.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					operacao("soma");
				}
			});
			btnSoma.setFont(new Font("Tahoma", Font.PLAIN, 50));
			btnSoma.setBounds(364, 341, 72, 64);
			frmMisaelSilva.getContentPane().add(btnSoma);

			JButton btnMultiplicacao = new JButton("*");
			btnMultiplicacao.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					operacao("multiplicacao");
				}
			});
			btnMultiplicacao.setFont(new Font("Tahoma", Font.PLAIN, 50));
			btnMultiplicacao.setBounds(364, 193, 72, 64);
			frmMisaelSilva.getContentPane().add(btnMultiplicacao);

			JButton btnDivisao = new JButton("/");
			btnDivisao.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					operacao("divisao");
				}
			});
			btnDivisao.setFont(new Font("Tahoma", Font.PLAIN, 50));
			btnDivisao.setBounds(364, 114, 72, 64);
			frmMisaelSilva.getContentPane().add(btnDivisao);

			JButton btnPotencia = new JButton("^");
			btnPotencia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					operacao("potencia");
				}
			});
			btnPotencia.setFont(new Font("Tahoma", Font.PLAIN, 50));
			btnPotencia.setBounds(272, 114, 72, 64);
			frmMisaelSilva.getContentPane().add(btnPotencia);

			JButton btnSubtracao = new JButton("-");
			btnSubtracao.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					operacao("subtracao");
				}
			});
			btnSubtracao.setFont(new Font("Tahoma", Font.PLAIN, 50));
			btnSubtracao.setBounds(364, 268, 72, 64);
			frmMisaelSilva.getContentPane().add(btnSubtracao);

			JButton btnIgual = new JButton("=");
			btnIgual.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					igual();
				}
			});
			btnIgual.setFont(new Font("Tahoma", Font.PLAIN, 50));
			btnIgual.setBounds(364, 437, 72, 64);
			frmMisaelSilva.getContentPane().add(btnIgual);

			JButton btnC_1_2_1 = new JButton(",");
			btnC_1_2_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					virgula();
				}
			});
			btnC_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 50));
			btnC_1_2_1.setBounds(272, 437, 72, 64);
			frmMisaelSilva.getContentPane().add(btnC_1_2_1);
			frmMisaelSilva.setBackground(new Color(118, 109, 198));
			frmMisaelSilva.setBounds(100, 100, 475, 565);
			frmMisaelSilva.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}


