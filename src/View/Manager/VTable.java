package View.Manager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class VTable extends JFrame {
    private JTable table;

    public VTable(String title, DefaultTableModel tm) {
        super(title);
        this.setSize(new Dimension(400 * tm.getColumnCount(), 800));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.table = new JTable(tm) {
            public boolean isCellEditable(int data, int columns) {
                return false;
            }
            public Component prepareRenderer(TableCellRenderer r, int data, int columns) {
                Component c = super.prepareRenderer(r, data, columns);
                if (data % 2 == 0) c.setBackground(Color.lightGray);
                else c.setBackground(Color.white);
                if (isCellSelected(data, columns)) c.setBackground(Color.cyan);
                return c;
            }
        };
        this.table.setPreferredScrollableViewportSize(new Dimension(450, 63));
        this.table.setFillsViewportHeight(true);
        JScrollPane jps = new JScrollPane(this.table);
        add(jps);
    }
}
