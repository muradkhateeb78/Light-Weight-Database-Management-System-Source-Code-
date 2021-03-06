package LDBMSADMINGUI;
import java.io.File;
import java.util.Enumeration;
import javax.swing.Icon;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class DBMSModel {

	/*
	 * This class basically creates the JTree. Adding the nodes by looking into the file system views. It adds
	 * all the folders and sub-folders into the JTree catalogue of the workingFolder path that the user provides.
	 */
	
	private FileSystemView fileSystemView;

	public DBMSModel() {
		this.fileSystemView = FileSystemView.getFileSystemView();
	}

	public DefaultTreeModel createTreeModel() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		File file = new File(DriveClass.workingFolder);
		root.add(new DefaultMutableTreeNode(new DBMSNode(file)));
		addChildNodes(root);
		addGrandchildNodes(root);
		return new DefaultTreeModel(root);
	}

	public void addGrandchildNodes(DefaultMutableTreeNode root) {
		Enumeration<?> enumeration = root.children();
		while (enumeration.hasMoreElements()) {
			DefaultMutableTreeNode node =(DefaultMutableTreeNode) enumeration.nextElement();
			addChildNodes(node);
		}
	}

	private void addChildNodes(DefaultMutableTreeNode root) {
		Enumeration<?> enumeration = root.children();
		while (enumeration.hasMoreElements()) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) enumeration.nextElement();
			DBMSNode fileNode = (DBMSNode) node.getUserObject();
			File file = fileNode.getFile();
			if (file.isDirectory()) {
				for (File child : file.listFiles()) {
					if(!child.isFile()){
						node.add(new DefaultMutableTreeNode(new DBMSNode(child)));
					}
				}
			}
		}
	}

	public FileSystemView getFileSystemView() {
		return fileSystemView;
	}

	public Icon getFileIcon(File file) {
		return fileSystemView.getSystemIcon(file);
	}

	public String getFileText(File file) {
		return fileSystemView.getSystemDisplayName(file);
	}
}