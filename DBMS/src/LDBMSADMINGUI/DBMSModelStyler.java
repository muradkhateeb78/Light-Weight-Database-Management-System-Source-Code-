package LDBMSADMINGUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

public class DBMSModelStyler implements TreeCellRenderer {
	
	/*
	 * This class sets the look of the JTree catalogue. It sets the icons and fonts. It also sets what background
	 * colors to appear when any of the node of JTree catalogue is clicked.
	 */

	private DBMSModel model;
	private JLabel label;

	public DBMSModelStyler(DBMSModel model) {
		this.model = model;
		this.label = new JLabel(" ");
		this.label.setFont(new Font("serif", Font.PLAIN, 13));
		label.setOpaque(true);
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, 
			boolean leaf, int row, boolean hasFocus){
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		DBMSNode fileNode = (DBMSNode) node.getUserObject();
		ImageIcon imageIcon = null;
		if(fileNode != null){
			File file = fileNode.getFile();
			if(node.isLeaf() && !node.getParent().toString().equals(DriveClass.folderOfDatabase)){
				imageIcon = new ImageIcon(new ImageIcon("Files\\table.png").getImage().getScaledInstance(17, 15, Image.SCALE_DEFAULT));
			}
			else{
				imageIcon = new ImageIcon(new ImageIcon("Files\\database.png").getImage().getScaledInstance(17, 15, Image.SCALE_DEFAULT));
			}
			label.setIcon(imageIcon); 

			label.setText(model.getFileText(file));
		}else{
			label.setText(value.toString());
		}
		if(selected){
			label.setBackground(Color.GRAY);
			label.setForeground(Color.WHITE);
		}else{
			label.setBackground(Color.WHITE);
			label.setForeground(Color.BLACK);
		}
		return label;
	}
}