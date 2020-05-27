package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import ctrl.BookingCtr;
import ctrl.BookingCtrIF;
import ctrl.DataAccessException;
import ctrl.RoomCtr;
import ctrl.RoomCtrIF;
import model.ConferenceRoom;
import model.Employee;
import model.HotelRoom;
import model.HotelRoom.ROOM_TYPE;

import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JCheckBox;
import javax.swing.border.BevelBorder;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ItemEvent;
import java.awt.CardLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.toedter.components.JSpinField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.beans.PropertyChangeEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;


public class MainViewOverview extends JPanel {
	private JTable bookingTable;
	private JTextField txtSg;
	private JComboBox<Item<Employee>> comboEmployees;
	private JTextField txtNewEmpName;
	private JTextField txtNewEmpPhone;
	private JPanel panel_bookingView;
	private JPanel HomeScreen_RightScreenCards;
	CardLayout cl_HomeScreen_RightScreenCards = new CardLayout(0,0);
	CardLayout cl_MainScreenCards = new CardLayout(0,0);
	private JTable suitableRoomsTable;
	private JTextField txtNewCustomerName;
	private JTextField txtNewCustomerPhone;
	private JTextField txtNewCustomerEmail;
	private JTextField txtNewCustomerAddress;
	private JTextField txtNewCustomerZip;
	private JTextField txtNewCustomerCardNo;
	private JButton btnShowBooking;
	private BookingCtrIF bctr = new BookingCtr();
	private RoomCtrIF roomctr = new RoomCtr();
	private JTextField txtSg_1;
	private JTable tableCustomers;
	private JTextField txtNewCustomerCity;
	private BookingTableModel btm = new BookingTableModel();
	private CustomersTableModel customertm;
	private ConferenceRoomsTableModel ctm;
	private JScrollPane scrollPane_1;
	private JDateChooser dcFilterStartDate;
	private JDateChooser dcFilterEndDate;
	private SuitableRoomsTableModel srtm;
	private JButton btnSelectRoom;
	private JTable conferenceRoomsTable;
	private JScrollPane scrollPane_3;
	private JButton btnConfSelectRoom;
	private String previousRoom;
	private JButton btnEventSelectRoom;
	private JButton btnEventBooking_1;
	private JTable eventRoomsTable;
	private EventRoomsTableModel etm;
	private JDateChooser newHotelRoomBStartDate;
	private JDateChooser newHotelRoomBEndDate;
	private JSpinner spinner_AntalPersoner;
	private JPanel MainScreenCards;
	private JDateChooser confDateChooser;
	private JComboBox confComboStartTime;
	private JComboBox confComboEndTime;
	private JSpinner spinConfPplQty;
	private JSpinner spinEventPplQty;
	private JComboBox comboEventStartTime;
	private JComboBox comboEventEndTime;
	

	/**
	 * Create the panel.
	 */
	public MainViewOverview() {
		setBackground(MainView.primary);
		setSize(new Dimension(1306, 1007));
		setLayout(null);
		
		MainScreenCards = new JPanel();
		MainScreenCards.setBounds(0, 11, 1273, 606);
		add(MainScreenCards);
		MainScreenCards.setLayout(cl_MainScreenCards);
		
		JPanel HomeScreen = new JPanel();
		MainScreenCards.add(HomeScreen, "mainHomeScreen");
		HomeScreen.setLayout(null);
		HomeScreen.setBackground(MainView.primary);
		
		JPanel HomeScreen_LeftScreenCards = new JPanel();
		HomeScreen_LeftScreenCards.setBounds(0, 0, 324, 606);
		HomeScreen.add(HomeScreen_LeftScreenCards);
		HomeScreen_LeftScreenCards.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 324, 606);
		HomeScreen_LeftScreenCards.add(panel);
		panel.setBackground(MainView.secondary);
		panel.setLayout(null);
		
		JButton btnHotelBooking = new JButton("Opret ny hotel booking");
		btnHotelBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = (Item) comboEmployees.getSelectedItem();
				Employee employee = (Employee) item.getValue();
				cl_MainScreenCards.show(MainScreenCards, "hotelBookingPanel");
				bctr.createBooking(employee);
				
			}
		});
		btnHotelBooking.setForeground(Color.WHITE);
		btnHotelBooking.setBackground(new Color(220, 198, 133));
		btnHotelBooking.setBounds(10, 57, 304, 29);
		panel.add(btnHotelBooking);
		
		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setBounds(0, 11, 324, 22);
		panel.add(lblMenu);
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setForeground(Color.WHITE);
		lblMenu.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton btnRoomOverview = new JButton("V�relsesoversigt");
		btnRoomOverview.setForeground(Color.WHITE);
		btnRoomOverview.setBackground(new Color(220, 198, 133));
		btnRoomOverview.setBounds(10, 185, 304, 29);
		panel.add(btnRoomOverview);
		
		JButton btnConferenceBooking = new JButton("Opret ny konference booking");
		btnConferenceBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = (Item) comboEmployees.getSelectedItem();
				Employee employee = (Employee) item.getValue();
				cl_MainScreenCards.show(MainScreenCards, "conferenceBookingPanel");
				bctr.createBooking(employee);
			}
		});
		btnConferenceBooking.setForeground(Color.WHITE);
		btnConferenceBooking.setBackground(new Color(220, 198, 133));
		btnConferenceBooking.setBounds(10, 95, 304, 29);
		panel.add(btnConferenceBooking);
		
		JButton btnEventBooking;
		btnEventBooking_1 = new JButton("Opret ny event booking");
		btnEventBooking_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = (Item) comboEmployees.getSelectedItem();
				Employee employee = (Employee) item.getValue();
				cl_MainScreenCards.show(MainScreenCards, "eventBookingPanel");
				bctr.createBooking(employee);
			}
		});
		btnEventBooking_1.setForeground(Color.WHITE);
		btnEventBooking_1.setBackground(new Color(220, 198, 133));
		btnEventBooking_1.setBounds(10, 133, 304, 29);
		panel.add(btnEventBooking_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(MainView.secondary);
		panel_3.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "  Medarbejder  ", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_3.setBounds(4, 545, 316, 56);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		comboEmployees = new JComboBox<Item<Employee>>();
		comboEmployees.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (comboEmployees.getSelectedIndex() == -1) {
					btnHotelBooking.setEnabled(false);
					btnConferenceBooking.setEnabled(false);
					btnEventBooking_1.setEnabled(false);
					btnRoomOverview.setEnabled(false);
					btnShowBooking.setEnabled(false);
				}
				else {
					btnHotelBooking.setEnabled(true);
					btnConferenceBooking.setEnabled(true);
					btnEventBooking_1.setEnabled(true);
					btnRoomOverview.setEnabled(true);
					btnShowBooking.setEnabled(true);
				}
			}
		});
		comboEmployees.setBounds(10, 16, 296, 29);
		btnHotelBooking.setEnabled(false);
		btnConferenceBooking.setEnabled(false);
		btnEventBooking_1.setEnabled(false);
		btnRoomOverview.setEnabled(false);
		panel_3.add(comboEmployees);
		comboEmployees.setBackground(MainView.primary);
		comboEmployees.setForeground(MainView.textColor);
		
		JButton btnNewEmployee = new JButton("Opret ny medarbejder");
		btnNewEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_HomeScreen_RightScreenCards.show(HomeScreen_RightScreenCards, "newEmpScreen");
			}
		});
		btnNewEmployee.setForeground(Color.WHITE);
		btnNewEmployee.setBackground(new Color(220, 198, 133));
		btnNewEmployee.setBounds(10, 455, 304, 29);
		panel.add(btnNewEmployee);
		
		JButton btnCreateNewRoom = new JButton("Opret nyt v\u00E6relse");
		btnCreateNewRoom.setForeground(Color.WHITE);
		btnCreateNewRoom.setBackground(new Color(220, 198, 133));
		btnCreateNewRoom.setBounds(10, 500, 304, 29);
		panel.add(btnCreateNewRoom);
		
		HomeScreen_RightScreenCards = new JPanel();
		HomeScreen_RightScreenCards.setBounds(332, 0, 949, 606);
		HomeScreen.add(HomeScreen_RightScreenCards);
		HomeScreen_RightScreenCards.setLayout(cl_HomeScreen_RightScreenCards);
		
		
		panel_bookingView = new JPanel();
		HomeScreen_RightScreenCards.add(panel_bookingView, "homeScreen");
		panel_bookingView.setBackground(MainView.secondary);
		panel_bookingView.setLayout(null);
		
		
		
		JLabel lblVrelser = new JLabel("Bookingoversigt");
		lblVrelser.setForeground(Color.WHITE);
		lblVrelser.setHorizontalAlignment(SwingConstants.CENTER);
		lblVrelser.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblVrelser.setBounds(0, 11, 932, 22);
		panel_bookingView.add(lblVrelser);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 91, 922, 282);
		panel_bookingView.add(scrollPane_1);
		
		bookingTable = new JTable();
		scrollPane_1.setViewportView(bookingTable);
		bookingTable.setBackground(MainView.primary);
		bookingTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.WHITE);
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), new Color(160, 160, 160)), "Tidsrum", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_2.setBackground(MainView.primary);
		
		panel_2.setBounds(10, 384, 171, 211);
		panel_bookingView.add(panel_2);
		panel_2.setLayout(null);
		
		JRadioButton rdbFuture = new JRadioButton("Fremtid");
		rdbFuture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btm.filterForward();
				bookingTable.updateUI();
			}
		});
		rdbFuture.setBounds(6, 48, 145, 23);
		rdbFuture.setBackground(MainView.primary);
		rdbFuture.setForeground(MainView.textColor);
		panel_2.add(rdbFuture);
		
		JRadioButton rdbWeek = new JRadioButton("1 uge frem");
		rdbWeek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btm.filterWeek();
				bookingTable.updateUI();
			}
		});
		rdbWeek.setBackground(MainView.primary);
		rdbWeek.setForeground(MainView.textColor);
		rdbWeek.setBounds(6, 74, 145, 23);
		panel_2.add(rdbWeek);
		
		JRadioButton rdbMonth = new JRadioButton("1 måned frem");
		rdbMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btm.filterMonth();
				bookingTable.updateUI();
			}
		});
		rdbMonth.setBounds(6, 100, 145, 23);
		rdbMonth.setBackground(MainView.primary);
		rdbMonth.setForeground(MainView.textColor);
		panel_2.add(rdbMonth);
		
		JRadioButton rdbCustom = new JRadioButton("Brugerdefineret");
		rdbCustom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btm.filterCustom(dcFilterStartDate.getDate(), dcFilterEndDate.getDate());
				bookingTable.updateUI();
			}
		});
		rdbCustom.setBounds(6, 126, 145, 23);
		rdbCustom.setBackground(MainView.primary);
		rdbCustom.setForeground(MainView.textColor);
		panel_2.add(rdbCustom);
		
		JRadioButton rdbNow = new JRadioButton("Igangværende");
		rdbNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btm.filterOngoing();
				bookingTable.updateUI();
			}
		});
		rdbNow.setSelected(true);
		rdbNow.setForeground(Color.WHITE);
		rdbNow.setBackground(new Color(220, 198, 133));
		rdbNow.setBounds(6, 22, 145, 23);
		panel_2.add(rdbNow);
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbNow);
		bg.add(rdbFuture);
		bg.add(rdbWeek);
		bg.add(rdbMonth);
		bg.add(rdbCustom);
		dcFilterStartDate = new JDateChooser();
		dcFilterStartDate.setDate(Calendar.getInstance().getTime());
		dcFilterStartDate.setBounds(6, 156, 145, 20);
		panel_2.add(dcFilterStartDate);
		
		dcFilterEndDate = new JDateChooser();
		dcFilterEndDate.setDate(Calendar.getInstance().getTime());
		dcFilterEndDate.setBounds(6, 180, 145, 20);
		panel_2.add(dcFilterEndDate);
		
		
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setForeground(Color.WHITE);
		panel_2_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), new Color(160, 160, 160)), "Statistik", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_2_1.setBackground(new Color(220, 198, 133));
		panel_2_1.setBounds(189, 407, 743, 188);
		panel_bookingView.add(panel_2_1);
		
		txtSg = new JTextField();
		txtSg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSg.setText("Søg");
		txtSg.setBounds(10, 58, 151, 22);
		panel_bookingView.add(txtSg);
		txtSg.setColumns(10);
		
		JPanel panel_newEmp = new JPanel();
		panel_newEmp.setLayout(null);
		panel_newEmp.setBackground(new Color(57, 59, 63));
		HomeScreen_RightScreenCards.add(panel_newEmp, "newEmpScreen");
		
		txtNewEmpName = new JTextField();
		txtNewEmpName.setBounds(15, 57, 146, 26);
		panel_newEmp.add(txtNewEmpName);
		txtNewEmpName.setColumns(10);
		
		txtNewEmpPhone = new JTextField();
		txtNewEmpPhone.setColumns(10);
		txtNewEmpPhone.setBounds(15, 102, 146, 26);
		panel_newEmp.add(txtNewEmpPhone);
		
		JButton btnCreateNewEmp = new JButton("Opret Medarbejder");
		btnCreateNewEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_HomeScreen_RightScreenCards.show(HomeScreen_RightScreenCards, "homeScreen");
			}
		});
		btnCreateNewEmp.setBounds(36, 158, 205, 29);
		panel_newEmp.add(btnCreateNewEmp);
		cl_HomeScreen_RightScreenCards.addLayoutComponent(panel_bookingView, "homeScreen");
		
		btnShowBooking = new JButton("Se booking");
		btnShowBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_MainScreenCards.show(MainScreenCards, "showBookingPanel");
			}
		});
		btnShowBooking.setForeground(Color.WHITE);
		btnShowBooking.setEnabled(false);
		btnShowBooking.setBackground(new Color(220, 198, 133));
		btnShowBooking.setBounds(826, 376, 106, 29);
		panel_bookingView.add(btnShowBooking);
		cl_HomeScreen_RightScreenCards.addLayoutComponent(panel_newEmp, "newEmpScreen");
		cl_MainScreenCards.addLayoutComponent(HomeScreen, "mainHomeScreen");
		
		JPanel NewHotelBookingPanel = new JPanel();
		NewHotelBookingPanel.setBackground(MainView.primary);
		NewHotelBookingPanel.setLayout(null);
		MainScreenCards.add(NewHotelBookingPanel, "hotelBookingPanel");
		cl_MainScreenCards.addLayoutComponent(NewHotelBookingPanel, "hotelBookingPanel");
		
		JPanel NewHotelBooking_LeftScreenCards = new JPanel();
		NewHotelBooking_LeftScreenCards.setBounds(0, 0, 324, 606);
		NewHotelBookingPanel.add(NewHotelBooking_LeftScreenCards);
		NewHotelBooking_LeftScreenCards.setLayout(new CardLayout(0, 0));
		
		JPanel panel_filterRooms = new JPanel();
		NewHotelBooking_LeftScreenCards.add(panel_filterRooms, "name_173838045110500");
		panel_filterRooms.setLayout(null);
		panel_filterRooms.setBackground(new Color(57, 59, 63));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Suite", "Lejlighed", "Handikapvenlig", "Superior", "Superior Family", "Comfort", "Comfort Family", "Standard"}));
		comboBox.setForeground(Color.WHITE);
		comboBox.setBackground(new Color(220, 198, 133));
		comboBox.setBounds(159, 76, 153, 22);
		panel_filterRooms.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Filtrer");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(0, 11, 324, 22);
		panel_filterRooms.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("V\u00E6relsestype");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 78, 97, 14);
		panel_filterRooms.add(lblNewLabel_2);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4"}));
		comboBox_1.setForeground(Color.WHITE);
		comboBox_1.setBackground(new Color(220, 198, 133));
		comboBox_1.setBounds(159, 109, 153, 22);
		panel_filterRooms.add(comboBox_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Enkeltsenge");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBackground(new Color(220, 198, 133));
		lblNewLabel_2_1.setBounds(10, 111, 107, 20);
		panel_filterRooms.add(lblNewLabel_2_1);
		
		newHotelRoomBStartDate = new JDateChooser();
		newHotelRoomBStartDate.setDate(Calendar.getInstance().getTime());
		newHotelRoomBStartDate.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if(newHotelRoomBEndDate.getDate().getTime()<=newHotelRoomBStartDate.getDate().getTime()) {
					Calendar newDate = newHotelRoomBStartDate.getCalendar();
					newDate.add(Calendar.DATE, 1);
					newHotelRoomBEndDate.setDate(newDate.getTime());
				}
				if(newHotelRoomBStartDate.getDate().getTime()<Calendar.getInstance().getTimeInMillis()) {
					newHotelRoomBStartDate.setDate(Calendar.getInstance().getTime());
				}
			}
		});
		
		newHotelRoomBStartDate.setBounds(159, 258, 153, 20);
		panel_filterRooms.add(newHotelRoomBStartDate);
		
		newHotelRoomBEndDate = new JDateChooser();
		newHotelRoomBEndDate.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if(newHotelRoomBEndDate.getDate().getTime()<=newHotelRoomBStartDate.getDate().getTime()) {
					Calendar newDate = newHotelRoomBStartDate.getCalendar();
					newDate.add(Calendar.DATE, 1);
					newHotelRoomBEndDate.setDate(newDate.getTime());
				}
			}
		});
		Calendar firstEndDate = Calendar.getInstance();
		firstEndDate.add(Calendar.DATE, 1);
		newHotelRoomBEndDate.setDate(firstEndDate.getTime());
		newHotelRoomBEndDate.setBackground(new Color(220, 198, 133));
		newHotelRoomBEndDate.setBounds(159, 294, 153, 20);
		panel_filterRooms.add(newHotelRoomBEndDate);
		
		JLabel lblNewLabel_2_2 = new JLabel("Start dato");
		lblNewLabel_2_2.setForeground(Color.WHITE);
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_2.setBounds(10, 258, 107, 20);
		panel_filterRooms.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Slut dato");
		lblNewLabel_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_1.setBackground(new Color(220, 198, 133));
		lblNewLabel_2_1_1.setBounds(10, 294, 117, 20);
		panel_filterRooms.add(lblNewLabel_2_1_1);
		
		JButton btn_Back = new JButton("Tilbage");
		btn_Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_MainScreenCards.first(MainScreenCards);
			}
		});
		btn_Back.setForeground(Color.WHITE);
		btn_Back.setBackground(new Color(220, 198, 133));
		btn_Back.setBounds(15, 529, 130, 23);
		panel_filterRooms.add(btn_Back);
		
		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4"}));
		comboBox_1_1.setForeground(Color.WHITE);
		comboBox_1_1.setBackground(new Color(220, 198, 133));
		comboBox_1_1.setBounds(159, 142, 153, 22);
		panel_filterRooms.add(comboBox_1_1);
		
		JLabel lblNewLabel_2_1_3 = new JLabel("Dobbeltsenge");
		lblNewLabel_2_1_3.setForeground(Color.WHITE);
		lblNewLabel_2_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_3.setBackground(new Color(220, 198, 133));
		lblNewLabel_2_1_3.setBounds(10, 144, 107, 20);
		panel_filterRooms.add(lblNewLabel_2_1_3);
		
		JCheckBox checkIsFamily = new JCheckBox("");
		checkIsFamily.setHorizontalAlignment(SwingConstants.CENTER);
		checkIsFamily.setBackground(new Color(57, 59, 63));
		checkIsFamily.setBounds(284, 223, 28, 23);
		panel_filterRooms.add(checkIsFamily);
		
		JLabel lblFamily = new JLabel("Familiev\u00E6relse (inkl. k\u00F8jeseng)");
		lblFamily.setForeground(Color.WHITE);
		lblFamily.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFamily.setBackground(new Color(220, 198, 133));
		lblFamily.setBounds(10, 223, 273, 26);
		panel_filterRooms.add(lblFamily);
		
		JLabel AntalPersoner = new JLabel("Antal Personer");
		AntalPersoner.setForeground(Color.WHITE);
		AntalPersoner.setFont(new Font("Tahoma", Font.PLAIN, 14));
		AntalPersoner.setBackground(new Color(220, 198, 133));
		AntalPersoner.setBounds(10, 187, 107, 20);
		panel_filterRooms.add(AntalPersoner);
		
		spinner_AntalPersoner = new JSpinner();
		spinner_AntalPersoner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner_AntalPersoner.setBounds(264, 185, 48, 26);
		panel_filterRooms.add(spinner_AntalPersoner);
		
		JPanel NewHotelBooking_RightScreenCards = new JPanel();
		NewHotelBooking_RightScreenCards.setBounds(332, 0, 949, 606);
		NewHotelBookingPanel.add(NewHotelBooking_RightScreenCards);
		NewHotelBooking_RightScreenCards.setLayout(new CardLayout(0, 0));
		
		JPanel panel_filteredRooms = new JPanel();
		panel_filteredRooms.setLayout(null);
		panel_filteredRooms.setBackground(new Color(57, 59, 63));
		NewHotelBooking_RightScreenCards.add(panel_filteredRooms, "name_173902167952300");
		
		JLabel lblVrelser_1 = new JLabel("Egnede værelser");
		lblVrelser_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblVrelser_1.setForeground(Color.WHITE);
		lblVrelser_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblVrelser_1.setBounds(0, 11, 932, 22);
		panel_filteredRooms.add(lblVrelser_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 75, 922, 441);
		panel_filteredRooms.add(scrollPane_2);
		
		suitableRoomsTable = new JTable();
		suitableRoomsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnSelectRoom.setEnabled(true);
			}
		});
		scrollPane_2.setViewportView(suitableRoomsTable);
		suitableRoomsTable.setBackground(new Color(220, 198, 133));
		
		btnSelectRoom = new JButton("Vælg værelse");
		btnSelectRoom.setEnabled(false);
		btnSelectRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bctr.getCurrentBooking().setR(roomctr.getHotelRoomById((int) suitableRoomsTable.getValueAt(suitableRoomsTable.getSelectedRow(), 0)));
				bctr.getCurrentBooking().setPeopleQty(Integer.parseInt(spinner_AntalPersoner.getValue().toString()));
				bctr.getCurrentBooking().setTimeStart(newHotelRoomBStartDate.getCalendar());
				bctr.getCurrentBooking().setTimeEnd(newHotelRoomBEndDate.getCalendar());
				bctr.getCurrentBooking().getTimeStart().set(Calendar.HOUR_OF_DAY, 10);
				bctr.getCurrentBooking().getTimeStart().set(Calendar.MINUTE, 0);
				bctr.getCurrentBooking().getTimeStart().set(Calendar.SECOND, 0);
				bctr.getCurrentBooking().getTimeStart().set(Calendar.MILLISECOND, 0);
				bctr.getCurrentBooking().getTimeEnd().set(Calendar.HOUR_OF_DAY, 14);
				bctr.getCurrentBooking().getTimeEnd().set(Calendar.MINUTE, 0);
				bctr.getCurrentBooking().getTimeEnd().set(Calendar.SECOND, 0);
				bctr.getCurrentBooking().getTimeEnd().set(Calendar.MILLISECOND, 0);
				cl_MainScreenCards.show(MainScreenCards, "chooseCustomerPanel");
				previousRoom = "hotelBookingPanel";
			}
		});
		btnSelectRoom.setForeground(Color.WHITE);
		btnSelectRoom.setBackground(new Color(220, 198, 133));
		btnSelectRoom.setBounds(802, 523, 130, 23);
		panel_filteredRooms.add(btnSelectRoom);
		
		JPanel ChooseCustomerPanel = new JPanel();
		ChooseCustomerPanel.setBackground(MainView.primary);
		ChooseCustomerPanel.setLayout(null);
		MainScreenCards.add(ChooseCustomerPanel, "chooseCustomerPanel");
		cl_MainScreenCards.addLayoutComponent(ChooseCustomerPanel, "chooseCustomerPanel");
		
		JPanel ChooseCustomer_LeftScreenCards = new JPanel();
		ChooseCustomer_LeftScreenCards.setBounds(0, 0, 324, 606);
		ChooseCustomerPanel.add(ChooseCustomer_LeftScreenCards);
		ChooseCustomer_LeftScreenCards.setLayout(new CardLayout(0, 0));
		
		JPanel selectOrCreateNewCustomer = new JPanel();
		selectOrCreateNewCustomer.setLayout(null);
		selectOrCreateNewCustomer.setBackground(new Color(57, 59, 63));
		ChooseCustomer_LeftScreenCards.add(selectOrCreateNewCustomer, "name_175322861620900");
		
		JLabel lblForm = new JLabel("Opret ny kunde");
		lblForm.setHorizontalAlignment(SwingConstants.CENTER);
		lblForm.setForeground(Color.WHITE);
		lblForm.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblForm.setBounds(0, 260, 324, 22);
		selectOrCreateNewCustomer.add(lblForm);
		
		txtNewCustomerName = new JTextField("Navn");
		txtNewCustomerName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		txtNewCustomerName.setForeground(Color.BLACK);
		txtNewCustomerName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtNewCustomerName.setBounds(10, 293, 304, 22);
		selectOrCreateNewCustomer.add(txtNewCustomerName);
		
		txtNewCustomerPhone = new JTextField("Telefon");
		txtNewCustomerPhone.setForeground(Color.BLACK);
		txtNewCustomerPhone.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtNewCustomerPhone.setBounds(10, 324, 304, 22);
		selectOrCreateNewCustomer.add(txtNewCustomerPhone);
		
		txtNewCustomerEmail = new JTextField("Email");
		txtNewCustomerEmail.setForeground(Color.BLACK);
		txtNewCustomerEmail.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtNewCustomerEmail.setBounds(10, 357, 304, 22);
		selectOrCreateNewCustomer.add(txtNewCustomerEmail);
		
		txtNewCustomerAddress = new JTextField("Adresse");
		txtNewCustomerAddress.setForeground(Color.BLACK);
		txtNewCustomerAddress.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtNewCustomerAddress.setBounds(10, 413, 304, 22);
		selectOrCreateNewCustomer.add(txtNewCustomerAddress);
		
		txtNewCustomerZip = new JTextField("Postnummer");
		txtNewCustomerZip.setForeground(Color.BLACK);
		txtNewCustomerZip.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtNewCustomerZip.setBounds(10, 446, 88, 22);
		selectOrCreateNewCustomer.add(txtNewCustomerZip);
		
		JComboBox comboCountry = new JComboBox();
		comboCountry.setModel(new DefaultComboBoxModel(new String[] {"Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua & Deps", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Central African Rep", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Congo {Democratic Rep}", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland {Republic}", "Israel", "Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea North", "Korea South", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar, {Burma}", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russian Federation", "Rwanda", "St Kitts & Nevis", "St Lucia", "Saint Vincent & the Grenadines", "Samoa", "San Marino", "Sao Tome & Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad & Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe"}));
		comboCountry.setSelectedIndex(45);
		comboCountry.setBounds(73, 479, 241, 22);
		selectOrCreateNewCustomer.add(comboCountry);
		
		JLabel lblNewLabel_1 = new JLabel(" Land");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setBounds(10, 479, 75, 22);
		selectOrCreateNewCustomer.add(lblNewLabel_1);
		
		txtNewCustomerCardNo = new JTextField("Kortnummer");
		txtNewCustomerCardNo.setForeground(Color.BLACK);
		txtNewCustomerCardNo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtNewCustomerCardNo.setBounds(10, 512, 304, 22);
		selectOrCreateNewCustomer.add(txtNewCustomerCardNo);
		
		JLabel lblVlgEksisterendeKunde = new JLabel("V\u00E6lg kunde");
		lblVlgEksisterendeKunde.setHorizontalAlignment(SwingConstants.CENTER);
		lblVlgEksisterendeKunde.setForeground(Color.WHITE);
		lblVlgEksisterendeKunde.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblVlgEksisterendeKunde.setBounds(0, 11, 324, 22);
		selectOrCreateNewCustomer.add(lblVlgEksisterendeKunde);
		
		txtSg_1 = new JTextField("S\u00F8g - Navn/Tlf");
		txtSg_1.setForeground(Color.BLACK);
		txtSg_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtSg_1.setBounds(10, 43, 304, 22);
		selectOrCreateNewCustomer.add(txtSg_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 75, 304, 169);
		selectOrCreateNewCustomer.add(scrollPane);
		
		tableCustomers = new JTable();
		scrollPane.setViewportView(tableCustomers);
		tableCustomers.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Navn", "Telefonnummer"
			}
		));
		
		txtNewCustomerCity = new JTextField("By");
		txtNewCustomerCity.setForeground(Color.BLACK);
		txtNewCustomerCity.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtNewCustomerCity.setBounds(124, 446, 185, 22);
		selectOrCreateNewCustomer.add(txtNewCustomerCity);
		
		JPanel ChooseCustomer_RightScreenCards = new JPanel();
		ChooseCustomer_RightScreenCards.setBounds(332, 0, 949, 606);
		ChooseCustomerPanel.add(ChooseCustomer_RightScreenCards);
		ChooseCustomer_RightScreenCards.setLayout(new CardLayout(0, 0));
		
		JPanel customerOverview = new JPanel();
		customerOverview.setLayout(null);
		customerOverview.setBackground(new Color(57, 59, 63));
		ChooseCustomer_RightScreenCards.add(customerOverview, "name_175395131656900");
		
		JLabel lblOversigt = new JLabel("Oversigt");
		lblOversigt.setHorizontalAlignment(SwingConstants.CENTER);
		lblOversigt.setForeground(Color.WHITE);
		lblOversigt.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblOversigt.setBounds(0, 11, 942, 22);
		customerOverview.add(lblOversigt);
		
		JButton btnBack = new JButton("Tilbage");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_MainScreenCards.show(MainScreenCards, previousRoom);
			}
		});
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color(220, 198, 133));
		btnBack.setBounds(723, 562, 79, 23);
		customerOverview.add(btnBack);
		
		JButton btnfinishBooking = new JButton("Opret Booking");
		btnfinishBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtNewCustomerName.getText().equalsIgnoreCase("Navn")) {
					try {
						if(!comboCountry.getSelectedItem().toString().equalsIgnoreCase("Denmark")) {
							txtNewCustomerZip.setText("960");
						}
						bctr.getCurrentBooking().setC((bctr.createCustomer(txtNewCustomerName.getText(),txtNewCustomerPhone.getText(),txtNewCustomerEmail.getText(), txtNewCustomerAddress.getText(),comboCountry.getSelectedItem().toString(),txtNewCustomerCardNo.getText(),Integer.parseInt(txtNewCustomerZip.getText()),txtNewCustomerCity.getText())));		
						if(previousRoom.equalsIgnoreCase("hotelBookingPanel")) {
							long daysBetween = ChronoUnit.DAYS.between(bctr.getCurrentBooking().getTimeStart().toInstant(), bctr.getCurrentBooking().getTimeEnd().toInstant());
							bctr.getCurrentBooking().setTotalPrice(bctr.getCurrentBooking().getR().getPrice()*daysBetween);
						}
						else {
							int startTime = bctr.getCurrentBooking().getTimeStart().get(Calendar.HOUR_OF_DAY);
							int endTime = bctr.getCurrentBooking().getTimeEnd().get(Calendar.HOUR_OF_DAY);
							int timeBetween = endTime - startTime;
							bctr.getCurrentBooking().setTotalPrice((bctr.getCurrentBooking().getR().getPrice()*timeBetween)*bctr.getCurrentBooking().getPeopleQty());
						}
						bctr.addBooking(bctr.getCurrentBooking());
						cl_MainScreenCards.first(MainScreenCards);
						startOver();
					} catch (NumberFormatException | DataAccessException e1) {
						e1.printStackTrace();
					}
					
				}
				else {
					
				}
			}
		});
		btnfinishBooking.setForeground(Color.WHITE);
		btnfinishBooking.setBackground(new Color(220, 198, 133));
		btnfinishBooking.setBounds(812, 562, 120, 23);
		customerOverview.add(btnfinishBooking);
		
		JPanel ShowBookingPanel = new JPanel();
		ShowBookingPanel.setBackground(MainView.primary);
		ShowBookingPanel.setLayout(null);
		MainScreenCards.add(ShowBookingPanel, "showBookingPanel");
		cl_MainScreenCards.addLayoutComponent(ShowBookingPanel, "showBookingPanel");
		
		JPanel ShowBooking_LeftScreenCards = new JPanel();
		ShowBooking_LeftScreenCards.setBounds(0, 0, 324, 606);
		ShowBookingPanel.add(ShowBooking_LeftScreenCards);
		ShowBooking_LeftScreenCards.setLayout(new CardLayout(0, 0));
		
		JPanel BookingInfoPanel = new JPanel();
		BookingInfoPanel.setLayout(null);
		BookingInfoPanel.setBackground(new Color(57, 59, 63));
		ShowBooking_LeftScreenCards.add(BookingInfoPanel, "name_176601122146600");
		
		JLabel lblNewLabel_3 = new JLabel("Booking");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setBounds(0, 11, 338, 22);
		BookingInfoPanel.add(lblNewLabel_3);
		
		JPanel panel_2_2 = new JPanel();
		panel_2_2.setLayout(null);
		panel_2_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), new Color(160, 160, 160)), "Kundeoplysninger", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_2_2.setBackground(new Color(220, 198, 133));
		panel_2_2.setBounds(10, 44, 299, 191);
		BookingInfoPanel.add(panel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("Navn");
		lblNewLabel_2_3.setForeground(Color.WHITE);
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_3.setBounds(6, 16, 124, 14);
		panel_2_2.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Telefon");
		lblNewLabel_2_1_2.setForeground(Color.WHITE);
		lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_2.setBackground(new Color(220, 198, 133));
		lblNewLabel_2_1_2.setBounds(6, 49, 124, 14);
		panel_2_2.add(lblNewLabel_2_1_2);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Email");
		lblNewLabel_2_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_2_1.setBounds(6, 74, 124, 26);
		panel_2_2.add(lblNewLabel_2_2_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Adresse");
		lblNewLabel_2_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1.setBackground(new Color(220, 198, 133));
		lblNewLabel_2_1_1_1.setBounds(6, 104, 124, 20);
		panel_2_2.add(lblNewLabel_2_1_1_1);
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Postnummer");
		lblNewLabel_2_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_1.setBackground(new Color(220, 198, 133));
		lblNewLabel_2_1_1_1_1.setBounds(6, 135, 124, 20);
		panel_2_2.add(lblNewLabel_2_1_1_1_1);
		
		JLabel lblNewLabel_2_1_1_1_1_1 = new JLabel("Land");
		lblNewLabel_2_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_1_1.setBackground(new Color(220, 198, 133));
		lblNewLabel_2_1_1_1_1_1.setBounds(6, 165, 124, 20);
		panel_2_2.add(lblNewLabel_2_1_1_1_1_1);
		
		JLabel lblNewLabel_2_3_1 = new JLabel("\"\"");
		lblNewLabel_2_3_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_3_1.setForeground(Color.WHITE);
		lblNewLabel_2_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_3_1.setBounds(105, 16, 179, 14);
		panel_2_2.add(lblNewLabel_2_3_1);
		
		JLabel lblNewLabel_2_1_2_1 = new JLabel("\"\"");
		lblNewLabel_2_1_2_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_2_1.setBackground(new Color(220, 198, 133));
		lblNewLabel_2_1_2_1.setBounds(105, 49, 179, 14);
		panel_2_2.add(lblNewLabel_2_1_2_1);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("\"\"");
		lblNewLabel_2_2_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_2_1_1.setBounds(105, 74, 179, 26);
		panel_2_2.add(lblNewLabel_2_2_1_1);
		
		JLabel lblNewLabel_2_1_1_2 = new JLabel("\"\"");
		lblNewLabel_2_1_1_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_2_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_1_2.setBackground(new Color(220, 198, 133));
		lblNewLabel_2_1_1_2.setBounds(105, 104, 179, 20);
		panel_2_2.add(lblNewLabel_2_1_1_2);
		
		JLabel lblNewLabel_2_1_1_1_2 = new JLabel("\"\"");
		lblNewLabel_2_1_1_1_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_1_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_2_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_2.setBackground(new Color(220, 198, 133));
		lblNewLabel_2_1_1_1_2.setBounds(105, 135, 179, 20);
		panel_2_2.add(lblNewLabel_2_1_1_1_2);
		
		JLabel lblNewLabel_2_1_1_1_1_1_1 = new JLabel("\"\"");
		lblNewLabel_2_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_1_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_1_1_1.setBackground(new Color(220, 198, 133));
		lblNewLabel_2_1_1_1_1_1_1.setBounds(105, 165, 179, 20);
		panel_2_2.add(lblNewLabel_2_1_1_1_1_1_1);
		
		JLabel lblNewLabel_2_1_1_1_1_2 = new JLabel("Start dato:");
		lblNewLabel_2_1_1_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_2_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_1_2.setBackground(new Color(220, 198, 133));
		lblNewLabel_2_1_1_1_1_2.setBounds(10, 246, 205, 20);
		BookingInfoPanel.add(lblNewLabel_2_1_1_1_1_2);
		
		JLabel lblNewLabel_2_1_1_1_1_1_1_1 = new JLabel("\"\"");
		lblNewLabel_2_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_1_1_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_1_1_1_1.setBackground(new Color(220, 198, 133));
		lblNewLabel_2_1_1_1_1_1_1_1.setBounds(109, 247, 200, 20);
		BookingInfoPanel.add(lblNewLabel_2_1_1_1_1_1_1_1);
		
		JLabel lblNewLabel_2_1_1_1_1_2_1 = new JLabel("Slut dato:");
		lblNewLabel_2_1_1_1_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_1_2_1.setBackground(new Color(220, 198, 133));
		lblNewLabel_2_1_1_1_1_2_1.setBounds(10, 277, 205, 20);
		BookingInfoPanel.add(lblNewLabel_2_1_1_1_1_2_1);
		
		JLabel lblNewLabel_2_1_1_1_1_1_1_1_1 = new JLabel("\"\"");
		lblNewLabel_2_1_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_1_1_1_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_1_1_1_1_1.setBackground(new Color(220, 198, 133));
		lblNewLabel_2_1_1_1_1_1_1_1_1.setBounds(109, 277, 200, 20);
		BookingInfoPanel.add(lblNewLabel_2_1_1_1_1_1_1_1_1);
		
		JLabel lblNewLabel_2_1_1_1_1_2_1_1 = new JLabel("Pris:");
		lblNewLabel_2_1_1_1_1_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1_1_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_1_2_1_1.setBackground(new Color(220, 198, 133));
		lblNewLabel_2_1_1_1_1_2_1_1.setBounds(10, 308, 205, 20);
		BookingInfoPanel.add(lblNewLabel_2_1_1_1_1_2_1_1);
		
		JLabel lblNewLabel_2_1_1_1_1_1_1_1_1_1 = new JLabel("\"\"");
		lblNewLabel_2_1_1_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2_1_1_1_1_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_1_1_1_1_1_1.setBackground(new Color(220, 198, 133));
		lblNewLabel_2_1_1_1_1_1_1_1_1_1.setBounds(109, 308, 200, 20);
		BookingInfoPanel.add(lblNewLabel_2_1_1_1_1_1_1_1_1_1);
		
		JPanel ShowBooking_RightScreenCards = new JPanel();
		ShowBooking_RightScreenCards.setBounds(332, 0, 949, 606);
		ShowBookingPanel.add(ShowBooking_RightScreenCards);
		ShowBooking_RightScreenCards.setLayout(new CardLayout(0, 0));
		
		JPanel BookingOptionsPanel = new JPanel();
		BookingOptionsPanel.setLayout(null);
		BookingOptionsPanel.setBackground(new Color(57, 59, 63));
		ShowBooking_RightScreenCards.add(BookingOptionsPanel, "name_176773256706800");
		
		JLabel lblVrelser_2 = new JLabel("Værelse");
		lblVrelser_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblVrelser_2.setForeground(Color.WHITE);
		lblVrelser_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblVrelser_2.setBounds(0, 11, 932, 22);
		BookingOptionsPanel.add(lblVrelser_2);
		
		JButton btnNewButton_2 = new JButton("Forlæng ophold");
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBackground(new Color(220, 198, 133));
		btnNewButton_2.setBounds(143, 572, 136, 23);
		BookingOptionsPanel.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Check ud");
		btnNewButton_2_1.setForeground(Color.WHITE);
		btnNewButton_2_1.setBackground(new Color(220, 198, 133));
		btnNewButton_2_1.setBounds(289, 572, 115, 23);
		BookingOptionsPanel.add(btnNewButton_2_1);
		
		JButton btnNewButton_2_2 = new JButton("Slet booking");
		btnNewButton_2_2.setForeground(Color.WHITE);
		btnNewButton_2_2.setBackground(new Color(220, 198, 133));
		btnNewButton_2_2.setBounds(10, 572, 123, 23);
		BookingOptionsPanel.add(btnNewButton_2_2);
		
		JButton btnBack_1 = new JButton("Tilbage");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_MainScreenCards.first(MainScreenCards);
			}
		});
		btnBack_1.setForeground(Color.WHITE);
		btnBack_1.setBackground(new Color(220, 198, 133));
		btnBack_1.setBounds(800, 572, 115, 23);
		BookingOptionsPanel.add(btnBack_1);
		
		JPanel NewConferenceBookingPanel = new JPanel();
		NewConferenceBookingPanel.setLayout(null);
		NewConferenceBookingPanel.setBackground(new Color(220, 198, 133));
		MainScreenCards.add(NewConferenceBookingPanel, "conferenceBookingPanel");
		
		JPanel NewConferenceBooking_LeftScreenCards = new JPanel();
		NewConferenceBooking_LeftScreenCards.setBounds(0, 0, 324, 606);
		NewConferenceBookingPanel.add(NewConferenceBooking_LeftScreenCards);
		NewConferenceBooking_LeftScreenCards.setLayout(new CardLayout(0, 0));
		
		JPanel ConfLeftScreenPanel = new JPanel();
		ConfLeftScreenPanel.setBackground(new Color(57, 59, 63));
		NewConferenceBooking_LeftScreenCards.add(ConfLeftScreenPanel, "name_514853081759100");
		ConfLeftScreenPanel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Filtrer");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4.setBounds(0, 0, 324, 22);
		ConfLeftScreenPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_2_4 = new JLabel("Antal personer");
		lblNewLabel_2_4.setForeground(Color.WHITE);
		lblNewLabel_2_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_4.setBounds(15, 63, 97, 14);
		ConfLeftScreenPanel.add(lblNewLabel_2_4);
		
		confDateChooser = new JDateChooser();
		confDateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if(confDateChooser.getDate().getTime()<Calendar.getInstance().getTimeInMillis()) {
					confDateChooser.setDate(Calendar.getInstance().getTime());
				}
			}
		});
		confDateChooser.setDate(Calendar.getInstance().getTime());
		confDateChooser.setBounds(184, 97, 113, 26);
		ConfLeftScreenPanel.add(confDateChooser);
		
		JLabel lblNewLabel_2_4_1 = new JLabel("V\u00E6lg dato");
		lblNewLabel_2_4_1.setForeground(Color.WHITE);
		lblNewLabel_2_4_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_4_1.setBounds(15, 101, 97, 22);
		ConfLeftScreenPanel.add(lblNewLabel_2_4_1);
		
		confComboStartTime = new JComboBox();
		confComboStartTime.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				if(confComboStartTime.getSelectedIndex()>confComboEndTime.getSelectedIndex()) {
					confComboEndTime.setSelectedIndex(confComboStartTime.getSelectedIndex());
				}
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
		});
		confComboStartTime.setModel(new DefaultComboBoxModel(new String[] {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"}));
		confComboStartTime.setBounds(184, 139, 113, 26);
		ConfLeftScreenPanel.add(confComboStartTime);
		
		confComboEndTime = new JComboBox();
		confComboEndTime.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				if(confComboStartTime.getSelectedIndex()>confComboEndTime.getSelectedIndex()) {
					confComboEndTime.setSelectedIndex(confComboStartTime.getSelectedIndex());
				}
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
		});
		confComboEndTime.setModel(new DefaultComboBoxModel(new String[] {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"}));
		confComboEndTime.setBounds(184, 195, 113, 26);
		ConfLeftScreenPanel.add(confComboEndTime);
		
		JLabel lblNewLabel_2_4_1_1 = new JLabel("Start");
		lblNewLabel_2_4_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_4_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_4_1_1.setBounds(15, 139, 97, 22);
		ConfLeftScreenPanel.add(lblNewLabel_2_4_1_1);
		
		JLabel lblNewLabel_2_4_1_1_1 = new JLabel("Slut");
		lblNewLabel_2_4_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_4_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_4_1_1_1.setBounds(15, 198, 97, 22);
		ConfLeftScreenPanel.add(lblNewLabel_2_4_1_1_1);
		
		JLabel lblNewLabel_2_4_1_1_1_1 = new JLabel("Tid: ");
		lblNewLabel_2_4_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_4_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_4_1_1_1_1.setBounds(184, 168, 97, 22);
		ConfLeftScreenPanel.add(lblNewLabel_2_4_1_1_1_1);
		
		spinConfPplQty = new JSpinner();
		spinConfPplQty.setModel(new SpinnerNumberModel(5, 5, 150, 1));
		spinConfPplQty.setBounds(249, 57, 48, 26);
		ConfLeftScreenPanel.add(spinConfPplQty);
		
		JButton btn_Back_1 = new JButton("Tilbage");
		btn_Back_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_MainScreenCards.first(MainScreenCards);
			}
		});
		btn_Back_1.setForeground(Color.WHITE);
		btn_Back_1.setBackground(new Color(220, 198, 133));
		btn_Back_1.setBounds(15, 567, 130, 23);
		ConfLeftScreenPanel.add(btn_Back_1);
		
		JPanel NewConferenceBooking_RightScreenCards = new JPanel();
		NewConferenceBooking_RightScreenCards.setBounds(332, 0, 949, 606);
		NewConferenceBookingPanel.add(NewConferenceBooking_RightScreenCards);
		NewConferenceBooking_RightScreenCards.setLayout(new CardLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(57, 59, 63));
		NewConferenceBooking_RightScreenCards.add(panel_1, "name_515294608838300");
		panel_1.setLayout(null);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 75, 922, 441);
		panel_1.add(scrollPane_3);
		
		conferenceRoomsTable = new JTable();
		conferenceRoomsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnConfSelectRoom.setEnabled(true);
			}
		});
		scrollPane_3.setViewportView(conferenceRoomsTable);
		conferenceRoomsTable.setBackground(new Color(220, 198, 133));
		
		btnConfSelectRoom = new JButton("V\u00E6lg v\u00E6relse");
		btnConfSelectRoom.setEnabled(false);
		btnConfSelectRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConferenceRoom r = null;
				try {
					r = bctr.getConferenceRoomById(Integer.parseInt(conferenceRoomsTable.getValueAt(conferenceRoomsTable.getSelectedRow(), 0).toString()));
				} catch (NumberFormatException | DataAccessException e1) {
					System.out.println("Can't find conferenceRoom");
				}
				bctr.getCurrentBooking().setTimeStart(confDateChooser.getCalendar());
				bctr.getCurrentBooking().getTimeStart().set(Calendar.HOUR_OF_DAY, Integer.parseInt(confComboStartTime.getSelectedItem().toString().substring(0, 2)));
				bctr.getCurrentBooking().getTimeStart().set(Calendar.MINUTE, 0);
				bctr.getCurrentBooking().getTimeStart().set(Calendar.SECOND, 0);
				bctr.getCurrentBooking().getTimeStart().set(Calendar.MILLISECOND, 0);
				bctr.getCurrentBooking().setTimeEnd(confDateChooser.getCalendar());
				bctr.getCurrentBooking().getTimeEnd().set(Calendar.HOUR_OF_DAY, Integer.parseInt(confComboEndTime.getSelectedItem().toString().substring(0, 2)));
				bctr.getCurrentBooking().getTimeEnd().set(Calendar.MINUTE, 0);
				bctr.getCurrentBooking().getTimeEnd().set(Calendar.SECOND, 0);
				bctr.getCurrentBooking().getTimeEnd().set(Calendar.MILLISECOND, 0);
				bctr.getCurrentBooking().setPeopleQty(Integer.parseInt(spinConfPplQty.getValue().toString()));
				bctr.getCurrentBooking().setR(r);
				cl_MainScreenCards.show(MainScreenCards, "chooseCustomerPanel");
				previousRoom = "conferenceBookingPanel";
			}
		});
		btnConfSelectRoom.setForeground(Color.WHITE);
		btnConfSelectRoom.setBackground(new Color(220, 198, 133));
		btnConfSelectRoom.setBounds(804, 532, 128, 23);
		panel_1.add(btnConfSelectRoom);
		
		JPanel NewEventBookingPanel = new JPanel();
		NewEventBookingPanel.setLayout(null);
		NewEventBookingPanel.setBackground(new Color(220, 198, 133));
		MainScreenCards.add(NewEventBookingPanel, "eventBookingPanel");
		
		JPanel NewEventBooking_LeftScreenCards = new JPanel();
		NewEventBooking_LeftScreenCards.setBounds(0, 0, 324, 606);
		NewEventBookingPanel.add(NewEventBooking_LeftScreenCards);
		NewEventBooking_LeftScreenCards.setLayout(new CardLayout(0, 0));
		
		JPanel EventLeftScreenPanel = new JPanel();
		EventLeftScreenPanel.setLayout(null);
		EventLeftScreenPanel.setBackground(new Color(57, 59, 63));
		NewEventBooking_LeftScreenCards.add(EventLeftScreenPanel, "name_196307562605100");
		
		JLabel lblNewLabel_4_1 = new JLabel("Filtrer");
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setForeground(Color.WHITE);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4_1.setBounds(0, 0, 324, 22);
		EventLeftScreenPanel.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_2_4_2 = new JLabel("Antal Personer");
		lblNewLabel_2_4_2.setForeground(Color.WHITE);
		lblNewLabel_2_4_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_4_2.setBounds(15, 63, 97, 14);
		EventLeftScreenPanel.add(lblNewLabel_2_4_2);
		
		spinEventPplQty = new JSpinner();
		spinEventPplQty.setModel(new SpinnerNumberModel(50, 50, 200, 1));
		spinEventPplQty.setBounds(249, 57, 48, 26);
		EventLeftScreenPanel.add(spinEventPplQty);
		
		JDateChooser eventDateChooser = new JDateChooser();
		eventDateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if(eventDateChooser.getDate().getTime()<Calendar.getInstance().getTimeInMillis()) {
					eventDateChooser.setDate(Calendar.getInstance().getTime());
				}
			}
		});
		eventDateChooser.setDate(Calendar.getInstance().getTime());
		eventDateChooser.setBounds(184, 97, 113, 26);
		EventLeftScreenPanel.add(eventDateChooser);
		
		JLabel lblNewLabel_2_4_1_2 = new JLabel("V\u00E6lg dato");
		lblNewLabel_2_4_1_2.setForeground(Color.WHITE);
		lblNewLabel_2_4_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_4_1_2.setBounds(15, 101, 97, 22);
		EventLeftScreenPanel.add(lblNewLabel_2_4_1_2);
		
		comboEventStartTime = new JComboBox();
		comboEventStartTime.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				if(comboEventStartTime.getSelectedIndex()>comboEventEndTime.getSelectedIndex()) {
					comboEventEndTime.setSelectedIndex(comboEventStartTime.getSelectedIndex());
				}
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
		});
		comboEventStartTime.setModel(new DefaultComboBoxModel(new String[] {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"}));
		comboEventStartTime.setBounds(184, 139, 113, 26);
		EventLeftScreenPanel.add(comboEventStartTime);
		
		comboEventEndTime = new JComboBox();
		comboEventEndTime.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				if(comboEventStartTime.getSelectedIndex()>comboEventEndTime.getSelectedIndex()) {
					comboEventEndTime.setSelectedIndex(comboEventStartTime.getSelectedIndex());
				}
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
		});
		comboEventEndTime.setModel(new DefaultComboBoxModel(new String[] {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"}));
		comboEventEndTime.setBounds(184, 195, 113, 26);
		EventLeftScreenPanel.add(comboEventEndTime);
		
		JLabel lblNewLabel_2_4_1_1_2 = new JLabel("Start");
		lblNewLabel_2_4_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_2_4_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_4_1_1_2.setBounds(15, 139, 97, 22);
		EventLeftScreenPanel.add(lblNewLabel_2_4_1_1_2);
		
		JLabel lblNewLabel_2_4_1_1_1_2 = new JLabel("Slut");
		lblNewLabel_2_4_1_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_2_4_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_4_1_1_1_2.setBounds(15, 198, 97, 22);
		EventLeftScreenPanel.add(lblNewLabel_2_4_1_1_1_2);
		
		JLabel lblNewLabel_2_4_1_1_1_1_1 = new JLabel("Tid: ");
		lblNewLabel_2_4_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_4_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_4_1_1_1_1_1.setBounds(184, 168, 97, 22);
		EventLeftScreenPanel.add(lblNewLabel_2_4_1_1_1_1_1);
		
		JButton btn_Back_1_1 = new JButton("Tilbage");
		btn_Back_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_MainScreenCards.first(MainScreenCards);
			}
		});
		btn_Back_1_1.setForeground(Color.WHITE);
		btn_Back_1_1.setBackground(new Color(220, 198, 133));
		btn_Back_1_1.setBounds(15, 567, 130, 23);
		EventLeftScreenPanel.add(btn_Back_1_1);
		
		
		JPanel NewEventBooking_RightScreenCards = new JPanel();
		NewEventBooking_RightScreenCards.setBounds(332, 0, 949, 606);
		NewEventBookingPanel.add(NewEventBooking_RightScreenCards);
		NewEventBooking_RightScreenCards.setLayout(new CardLayout(0, 0));
		
		JPanel EventRightScreenPanel = new JPanel();
		EventRightScreenPanel.setLayout(null);
		EventRightScreenPanel.setBackground(new Color(57, 59, 63));
		NewEventBooking_RightScreenCards.add(EventRightScreenPanel, "name_196336045009000");
		
		JButton btnEventSelectRoom = new JButton("V\u00E6lg v\u00E6relse");
		btnEventSelectRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bctr.getCurrentBooking().setTimeStart(eventDateChooser.getCalendar());
				bctr.getCurrentBooking().setTimeEnd(eventDateChooser.getCalendar());
				bctr.getCurrentBooking().getTimeStart().set(Calendar.HOUR_OF_DAY, Integer.parseInt(comboEventStartTime.getSelectedItem().toString().substring(0, 2)));
				bctr.getCurrentBooking().getTimeStart().set(Calendar.MINUTE,0);
				bctr.getCurrentBooking().getTimeStart().set(Calendar.SECOND,0);
				bctr.getCurrentBooking().getTimeStart().set(Calendar.MILLISECOND,0);
				bctr.getCurrentBooking().getTimeEnd().set(Calendar.HOUR_OF_DAY, Integer.parseInt(comboEventEndTime.getSelectedItem().toString().substring(0, 2)));
				bctr.getCurrentBooking().getTimeEnd().set(Calendar.MINUTE,0);
				bctr.getCurrentBooking().getTimeEnd().set(Calendar.SECOND,0);
				bctr.getCurrentBooking().getTimeEnd().set(Calendar.MILLISECOND,0);
				bctr.getCurrentBooking().setPeopleQty(Integer.parseInt(spinEventPplQty.getValue().toString()));
				try {
					bctr.getCurrentBooking().setR(bctr.getEventRoomById(Integer.parseInt(eventRoomsTable.getValueAt(eventRoomsTable.getSelectedRow(), 0).toString())));
				} catch (NumberFormatException | DataAccessException e1) {
					System.out.println("Can't find selected Eventroom");
				}
				cl_MainScreenCards.show(MainScreenCards, "chooseCustomerPanel");
				previousRoom = "eventBookingPanel";
			}
		});
		btnEventBooking = new JButton("Opret ny event booking");
		btnEventBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = (Item) comboEmployees.getSelectedItem();
				Employee employee = (Employee) item.getValue();
				cl_MainScreenCards.show(MainScreenCards, "eventBookingPanel");
				bctr.createBooking(employee);
			}
		});
		
		
		
		btnEventSelectRoom.setForeground(Color.WHITE);
		btnEventSelectRoom.setBackground(new Color(220, 198, 133));
		btnEventSelectRoom.setBounds(804, 532, 128, 23);
		EventRightScreenPanel.add(btnEventSelectRoom);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 75, 922, 441);
		EventRightScreenPanel.add(scrollPane_4);
		
		eventRoomsTable = new JTable();
		scrollPane_4.setViewportView(eventRoomsTable);
		eventRoomsTable.setBackground(new Color(220, 198, 133));
		for(Employee e : bctr.getAllEmployees()) {
			comboEmployees.addItem(new Item<Employee>(e, e.getName()));
		}

		
		
		
		
		
		init();
	}
	
	protected void startOver() {
		updateTables();
		bctr = new BookingCtr();
	}

	public void init() {
		updateTables();
	}

	public CardLayout getCardLayout() {
		return cl_MainScreenCards;
	}
	
	protected JPanel getMainScreen() {
		return MainScreenCards;
	}
	
	private void updateTables() {
		btm = new BookingTableModel();
		srtm = new SuitableRoomsTableModel();
		ctm = new ConferenceRoomsTableModel();
		customertm = new CustomersTableModel();
		etm = new EventRoomsTableModel();
		btm.filterOngoing();
		eventRoomsTable.setModel(etm);
		conferenceRoomsTable.setModel(ctm);
		suitableRoomsTable.setModel(srtm);
		bookingTable.setModel(btm);	
		tableCustomers.setModel(customertm);
	}

	
	public void updateRoomBookings() {
		
	}
}
