package textproc;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class BookReaderController {

	public BookReaderController(GeneralWordCounter counter) {
		SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 500, 500));
	}

	private void createWindow(GeneralWordCounter counter, String title, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();

		// create list, scrollbar add to window
		ArrayList<Map.Entry<String, Integer>> filterList = new ArrayList<>(counter.getWordList());
		filterList.removeIf(e -> Character.isDigit(e.getKey().charAt(0))); // Filtrerar bort siffror.

		SortedListModel<Map.Entry<String, Integer>> listModel = new SortedListModel<>(filterList);
		JList<Map.Entry<String, Integer>> listView = new JList<>(listModel);
		JScrollPane scrollPane = new JScrollPane(listView);
		scrollPane.setPreferredSize(new Dimension(400, 400));
		pane.add(scrollPane, BorderLayout.CENTER);

		// create buttons
		JPanel panel = new JPanel();
		JRadioButton alpha = new JRadioButton("Alphabetic");
		JRadioButton freq = new JRadioButton("Frequency");
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(alpha);
		btnGroup.add(freq);

		// assign buttons
		alpha.addActionListener(event -> listModel.sort((e1, e2) -> e1.getKey().compareTo(e2.getKey())));
		freq.addActionListener(event -> listModel.sort((e1, e2) -> e1.getValue() - e2.getValue()));

		// add search
		JTextField searchField = new JTextField();
		searchField.setPreferredSize(new Dimension(200, 20));
		JButton searchBtn = new JButton("Find");

		searchBtn.addActionListener(e -> {
			String searchWord = searchField.getText();

			// filter text
			String textFilter = searchWord.trim().toLowerCase();

			for (int i = 0; i < filterList.size(); i++) {
				if (filterList.get(i).getKey().equals(textFilter)) {
					listView.setSelectedIndex(i);
					listView.ensureIndexIsVisible(i);
					break;
				} else if (i == filterList.size() - 1) {
					JOptionPane.showMessageDialog(frame, "Word not found in list.");
				}
			}
			;
		});

		// Lägg till alla komponenter.

		panel.add(alpha);
		panel.add(freq);
		panel.add(searchField);
		panel.add(searchBtn);

		pane.add(panel, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);
	}
}