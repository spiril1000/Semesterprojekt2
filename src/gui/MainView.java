package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class MainView extends JFrame {

	private JPanel contentPane;
	private JPanel center;
	private CardLayout cl;
	public static Color primary = new Color(220, 198, 133);
	public static Color secondary = new Color(57, 59, 63);
	public static Color textColor = new Color(255,255,255);
	public static JLabel lblOpretOrdre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView() {
		BufferedImage logo = null;
		try {
			logo = ImageIO.read(new URL("https://kompashotel.dk/wp-content/uploads/kompashotelaalborg-nylogo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(primary);
		
		JPanel north = new JPanel();
		FlowLayout flowLayout = (FlowLayout) north.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		north.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		north.setBackground(secondary);
		contentPane.add(north, BorderLayout.NORTH);
		Image dimg = logo.getScaledInstance(100, 25,
		        logo.SCALE_SMOOTH);
		lblOpretOrdre = new JLabel(new ImageIcon(dimg));
		
		lblOpretOrdre.setForeground(textColor);
		lblOpretOrdre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblOpretOrdre.setHorizontalAlignment(SwingConstants.LEFT);
		north.add(lblOpretOrdre);
		
		center = new JPanel();
		center.setBorder(null);
		center.setBackground(secondary);
		contentPane.add(center, BorderLayout.CENTER);
		cl = new CardLayout(0, 0);
		center.setLayout(cl);
		
		JPanel mainViewOverview = new MainViewOverview();
		center.add(mainViewOverview, "KompasOversigt");
		cl.show(center, "KompasOversigt");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
		
	}
	
	

}
