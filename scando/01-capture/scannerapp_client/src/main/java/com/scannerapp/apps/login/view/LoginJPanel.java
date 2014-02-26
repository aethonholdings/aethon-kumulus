package com.scannerapp.apps.login.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.scannerapp.apps.component.EdittedTextField;
import com.scannerapp.apps.component.StyledButton;
import com.scannerapp.apps.component.StyledComboBox;
import com.scannerapp.apps.component.StyledLabel;
import com.scannerapp.apps.component.StyledPanel;
import com.scannerapp.apps.component.StyledPasswordField;
import com.scannerapp.apps.framework.ui.SteppedComboBoxUI;
import com.scannerapp.apps.framework.view.BaseJPanel;
import com.scannerapp.apps.utils.ConstantUtil;
import com.scannerapp.resources.IconRepository;

@SuppressWarnings("serial")
public class LoginJPanel extends BaseJPanel implements IconRepository,
		FocusListener, java.awt.event.ActionListener {

	protected boolean logout = false;
	protected boolean flag = true;
	private GridBagLayout gridBagLayout1 = new GridBagLayout();
	private GridBagLayout gridBagLayout2 = new GridBagLayout();
	private GridBagLayout gridBagLayout3 = new GridBagLayout();
	private GridBagLayout gridBagLayout4 = new GridBagLayout();
	private GridBagLayout gridBagLayout5 = new GridBagLayout();	
	private TitledBorder titledBorder5;

	private StyledPanel jPanel1 = new StyledPanel();
	private StyledLabel jlblPassword = new StyledLabel();
	private StyledPanel jpnlSecond = new StyledPanel();
	private StyledLabel jlblLogin = new StyledLabel();

	public EdittedTextField jtxtLogin = new EdittedTextField(15);
	protected StyledPasswordField jpsdPassword = new StyledPasswordField();

	private StyledPanel jpnlFirst = new StyledPanel();
	private StyledButton jbtnLogon = new StyledButton();
	private StyledPanel jpnlThird = new StyledPanel();
	private StyledLabel jlblImage = new StyledLabel();
	private StyledLabel jlblProject = new StyledLabel();
	protected StyledComboBox jcmbProject = new StyledComboBox();

	public LoginJPanel() {
		try {
			jbInit();			
			setController(new LoginJPanelController(this));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public LoginJPanelController controller() {
		return (LoginJPanelController) getController();
	}

	void jbInit() throws Exception {
		setButtonCaption();
		setLabelCaption();
		setToolTips();
		setForLogin();
		jtxtLogin.setForceToUpperCase(true);

		titledBorder5 = new TitledBorder("");

		this.setLayout(gridBagLayout1);

		jPanel1.setLayout(gridBagLayout5);
		jlblPassword.setText("Password : ");

		jpnlSecond.setLayout(gridBagLayout3);

		jpsdPassword.setSelectionEnd(15);
		jpsdPassword.setColumns(15);
		jpnlFirst.setLayout(gridBagLayout2);

		jbtnLogon.setIcon(APPLY_ICON);
		jpnlThird.setLayout(gridBagLayout4);
		jlblImage.setAlignmentY((float) 0.0);
		jlblImage.setHorizontalTextPosition(SwingConstants.LEADING);
		//jlblImage.setIconTextGap(10);
		jlblImage.setIcon(IconRepository.AETHON_LOGO);

		//jPanel1.setAlignmentX((float) 0.0);
		//jPanel1.setAlignmentY((float) 0.0);
		jPanel1.setBorder(titledBorder5);
		jPanel1.setMinimumSize(new Dimension(615, 585));
		jPanel1.setPreferredSize(new Dimension(615, 560));

		jtxtLogin.setMinimumSize(new Dimension(163, 21));
		jtxtLogin.setPreferredSize(new Dimension(163, 21));
		jpsdPassword.setMinimumSize(new Dimension(163, 21));
		jpsdPassword.setPreferredSize(new Dimension(163, 21));
		jcmbProject.setMinimumSize(new Dimension(235, 21));
		jcmbProject.setPreferredSize(new Dimension(235, 21));
		jpnlFirst.setMinimumSize(new Dimension(150, 350));
		jpnlFirst.setPreferredSize(new Dimension(150, 350));

		this.add(jPanel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						0, 0, 0, 0), 0, 0));

		jpnlFirst.add(jlblImage, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						0, 60, 0, 0), 0, 0));

		jpnlSecond.add(jlblLogin, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5,
						5, 5, 1), 0, 0));
		jpnlSecond.add(jtxtLogin, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5,
						5, 5, 5), 120, 0));

		jpnlSecond.add(jlblPassword, new GridBagConstraints(0, 1, 1, 1, 0.0,
				0.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(5, 5, 0, 1), 0, 0));
		jpnlSecond.add(jpsdPassword, new GridBagConstraints(1, 1, 1, 1, 0.0,
				0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 120, 0));

//		jpnlSecond.add(jlblProject, new GridBagConstraints(0, 4, 1, 1, 0.0,
//				0.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
//				new Insets(5, 5, 5, 1), 0, 0));
//		jpnlSecond.add(jcmbProject, new GridBagConstraints(1, 4, 1, 1, 0.0,
//				0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
//				new Insets(5, 5, 5, 5), 50, 0));

		jpnlThird.add(jbtnLogon, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						5, 5, 5, 5), 0, 0));

		jPanel1.add(jpnlFirst, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 5, 0, 5), 0, 35));
		jPanel1.add(jpnlSecond, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						0, 5, 0, 5), 0, 0));
		jPanel1.add(jpnlThird, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						0, 5, 0, 5), 0, 0));

		jbtnLogon.addActionListener((java.awt.event.ActionListener) this);
		jtxtLogin.addFocusListener((java.awt.event.FocusListener) this);
		jpsdPassword.addActionListener((java.awt.event.ActionListener) this);
		jbtnLogon.setRequestFocusEnabled(true);
		//jcmbProject.setUI(new SteppedComboBoxUI());
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbtnLogon || e.getSource() == jpsdPassword) {			
			controller().jbtnLogon_actionPerformed();
		}
	}


	public void setLabelCaption() {
		jlblLogin.setText(ConstantUtil.getApplicationConstant("loginIdLabel") + " :");
		jlblPassword.setText(ConstantUtil.getApplicationConstant("passwordLabel") + " :");
		jlblProject.setText(ConstantUtil.getApplicationConstant("projectLabel") + " :");
		//jlblImage.setText("Scanner App Logo");
	}

	public void setButtonCaption() {
		jbtnLogon.setText(ConstantUtil.getApplicationConstant("loginButton"));
	}

	public void setToolTips() {
		jbtnLogon.setToolTipText(ConstantUtil.getApplicationConstant("loginButtonTT"));
	}

	public void setShortcuts() {
		//jbtnLogon.setMnemonic(hBundle.get("jbtnLogonSC").toString().charAt(0));
	}

	public void cleanup() {
		jbtnLogon.removeActionListener((java.awt.event.ActionListener) this);
		jpsdPassword.addActionListener((java.awt.event.ActionListener) this);

		jpnlFirst = null;
		jpnlSecond = null;
		jpnlThird = null;
		jPanel1 = null;

		jlblLogin = null;
		jlblPassword = null;
		jbtnLogon = null;
		jtxtLogin = null;
		jpsdPassword = null;
		jlblImage = null;
		jlblProject = null;
		jcmbProject = null;

		titledBorder5 = null;
		gridBagLayout1 = null;
		gridBagLayout2 = null;
		gridBagLayout3 = null;
		gridBagLayout4 = null;
		gridBagLayout5 = null;		

		setController(null);
		super.cleanup();
	}

	public void setForLogin() {
		jbtnLogon.requestFocus();
	}

	public void resetForLogin() {
		jtxtLogin.setText("");
		jpsdPassword.setText("");
		logout = true;
		flag = true;
		jbtnLogon.requestFocus();
		jtxtLogin.requestFocus();
	}

	public void resetForLoginWOLogout() {
		jtxtLogin.setText("");
		jpsdPassword.setText("");
		flag = true;
		jbtnLogon.requestFocus();
		jtxtLogin.requestFocus();
	}

	@Override
	public void focusGained(FocusEvent arg0) {

	}

	@Override
	public void focusLost(FocusEvent e) {

	}

	/**
	 * @return the jtxtLogin
	 */
	public EdittedTextField getJtxtLogin() {
		return jtxtLogin;
	}

	/**
	 * @param jtxtLogin
	 *            the jtxtLogin to set
	 */
	public void setJtxtLogin(EdittedTextField jtxtLogin) {
		this.jtxtLogin = jtxtLogin;
	}

	/**
	 * @return the jpsdPassword
	 */
	public StyledPasswordField getJpsdPassword() {
		return jpsdPassword;
	}

	/**
	 * @param jpsdPassword
	 *            the jpsdPassword to set
	 */
	public void setJpsdPassword(StyledPasswordField jpsdPassword) {
		this.jpsdPassword = jpsdPassword;
	}

	/**
	 * @return the jcmbProject
	 */
	public StyledComboBox getJcmbProject() {
		return jcmbProject;
	}

	/**
	 * @param jcmbProject
	 *            the jcmbProject to set
	 */
	public void setJcmbProject(StyledComboBox jcmbProject) {
		this.jcmbProject = jcmbProject;
	}
}
