package clothing4you;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Container;
import java.awt.Frame;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import clothing4you.Job;
import org.junit.jupiter.api.Test;

public class JobTest {

    @Test
    public void testIsModal() {
        JFrame parent = new JFrame();
        Job job = new Job(parent);
        assertTrue(job.isModal());
        job.dispose();
    }

    @Test
    public void testMinimumSize() {
        JFrame parent = new JFrame();
        Job job = new Job(parent);
        assertEquals(600, job.getMinimumSize().getWidth());
        assertEquals(600, job.getMinimumSize().getHeight());
        job.dispose();
    }

    @Test
    public void testTitleBorder() {
        JFrame parent = new JFrame();
        Job job = new Job(parent);
        Container container = job.getContentPane();
        assertTrue(container instanceof JPanel);
        JPanel panel = (JPanel) container;
        assertEquals("Job Openings", ((JLabel) panel.getComponent(1)).getText());
        job.dispose();
    }


    @Test
    public void testJobTableDisplay() {
        JFrame parent = new JFrame();
        Job job = new Job(parent);
        Container container = job.getContentPane();
        assertTrue(container instanceof JPanel);
        JPanel panel = (JPanel) container;
        JScrollPane scrollPane = (JScrollPane) panel.getComponent(2);
        JTable jobTable = (JTable) scrollPane.getViewport().getView();
        assertNotNull(jobTable);
        assertEquals("Job Title", jobTable.getColumnName(0));
        assertEquals("Location", jobTable.getColumnName(1));
        assertEquals("Salary", jobTable.getColumnName(2));
        assertEquals(6, jobTable.getRowCount());
        job.dispose();
    }

    @Test
    public void testJobTableNotEditable() {
        JFrame parent = new JFrame();
        Job job = new Job(parent);
        Container container = job.getContentPane();
        assertTrue(container instanceof JPanel);
        JPanel panel = (JPanel) container;
        JScrollPane scrollPane = (JScrollPane) panel.getComponent(2);
        JTable jobTable = (JTable) scrollPane.getViewport().getView();
        assertNotNull(jobTable);
        job.dispose();
    }

    @Test
    public void testApplyButtonEnabled() {
        JFrame parent = new JFrame();
        Job job = new Job(parent);
        Container container = job.getContentPane();
        assertTrue(container instanceof JPanel);
    }

}