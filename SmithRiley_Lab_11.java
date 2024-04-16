import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

    public class FileLister extends JFrame
        {
            private JTextArea fileListTextArea;

                public FileLister()
                    {
                        setTitle("Recursive Lister");
                        setSize(600, 600);
                        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        setLocationRelativeTo(null);
                        JPanel mainPanel = new JPanel();
                        mainPanel.setLayout(new BorderLayout());
                        fileListTextArea = new JTextArea();
                        fileListTextArea.setEditable(false);
                        JScrollPane scrollPane = new JScrollPane(fileListTextArea);
                        JButton selectDirectoryButton = new JButton("Select Directory / File (NOT WHOLE DRIVE)");
                        selectDirectoryButton.addActionListener(new ActionListener()
                            {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    selectDirectory();
                                }
                            });
                        mainPanel.add(selectDirectoryButton, BorderLayout.NORTH);
                        mainPanel.add(scrollPane, BorderLayout.CENTER);
                        add(mainPanel);
                    }
                private void selectDirectory()
                    {
                        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                        fileChooser.setDialogTitle("Select Directory / File (NOT WHOLE DRIVE)");
                        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        int userSelection = fileChooser.showOpenDialog(this);
                        if (userSelection == JFileChooser.APPROVE_OPTION)
                            {
                                File directory = fileChooser.getSelectedFile();
                                listFiles(directory);
                            }
                    }
                private void listFiles(File directory)
                    {
                        fileListTextArea.setText("");
                        listFilesRecursive(directory);
                    }
                private void listFilesRecursive(File directory)
                {
                    File[] files = directory.listFiles();
                    if (files != null)
                        {
                            for (File file : files)
                                {
                                    if (file.isDirectory())
                                        {
                                            listFilesRecursive(file); 
                                        }
                                         else
                                            {
                                                fileListTextArea.append(file.getAbsolutePath() + "\n");
                                            }
                                }
                        }
                }
                public static void main(String[] args)
                {
                    SwingUtilities.invokeLater(new Runnable()
                        {
                            @Override
                            public void run()
                                {
                                    new FileLister().setVisible(true);
                                }
                        });
                }
            }
