import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

    //Constructor that assembles the components into a launchable frame//
    public class PizzaGUIFrame extends JFrame {
        public PizzaGUIFrame (String title) {
            super(title);
            setLayout(new FlowLayout());
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension screenSize = kit.getScreenSize();
            FlowLayout mainLineup = new FlowLayout();
            Dimension receiptSize = new Dimension();
            receiptSize.setSize((screenSize.getWidth()*0.3),(screenSize.getHeight()*0.7));
            PizzaCost currentPizzaPrice = new PizzaCost(0.0);
            ArrayList pizzaStringCombiner = new ArrayList<String>();

            //CRUST SELECT//
        JPanel mainPanel = new JPanel(mainLineup);
        this.add(mainPanel);
        mainPanel.setVisible(true);
        mainPanel.setSize(screenSize);

        JPanel crustPanel= new JPanel();
        Box crustBox = new Box(BoxLayout.Y_AXIS);
        ButtonGroup crustCluster = new ButtonGroup();

        JRadioButton thinCrustButton = new JRadioButton("Thin Crust");

        JRadioButton regularCrustButton = new JRadioButton("Regular Crust");
        JRadioButton deepDishButton = new JRadioButton("Deep Dish");

        PizzaParameter thinCrust =new PizzaParameter("Thin Crust",0.00);
        PizzaParameter regularCrust =new PizzaParameter("Regular Crust", 0.00);
        PizzaParameter deepDish =new PizzaParameter("Deep Dish", 0.00);

        crustCluster.add(thinCrustButton);
        crustCluster.add(regularCrustButton);
        crustCluster.add(deepDishButton);

        crustBox.add(thinCrustButton);
        crustBox.add(regularCrustButton);
        crustBox.add(deepDishButton);

        crustPanel.add(crustBox);
        crustBox.add(thinCrustButton);
        crustPanel.add(regularCrustButton);
        crustPanel.add(deepDishButton);
            ActionListener crustListener= new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (thinCrustButton.isSelected()){pizzaStringCombiner.add("\n Thin Crust, $0.00");};
                    if (regularCrustButton.isSelected()){pizzaStringCombiner.add("\n Regular Crust, $0.00");}
                    if (deepDishButton.isSelected()){pizzaStringCombiner.add("\n Deep Dish, $0.00");}

                }
            };
            thinCrustButton.addActionListener(crustListener);

            regularCrustButton.addActionListener(crustListener);
            deepDishButton.addActionListener(crustListener);
            mainPanel.add(crustPanel);



            JPanel sizePanel = new JPanel();
            String[] sizeComboList = new String[] {"Small","Medium","Large","Super"};


            JComboBox<String> sizeComboBox= new JComboBox<String>(sizeComboList);
            ActionListener sizeListener =new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   String sizeName=sizeComboBox.getSelectedItem().toString();
                    pizzaStringCombiner.add("\n" + sizeName);
                    if (sizeName=="Small"){
                        currentPizzaPrice.setCost(currentPizzaPrice.getCost()+8.00);
                        pizzaStringCombiner.add("\n Small               $8.00");
                    };
                    if (sizeName=="Medium"){currentPizzaPrice.setCost(currentPizzaPrice.getCost()+12.00);
                        pizzaStringCombiner.add("\n Medium              $12.00");
                    };
                    if (sizeName=="Large"){
                        currentPizzaPrice.setCost(currentPizzaPrice.getCost()+16.00);
                        pizzaStringCombiner.add("\n Large               $16.00");
                    };
                    if (sizeName=="Super"){
                        currentPizzaPrice.setCost(currentPizzaPrice.getCost()+20.00);
                        pizzaStringCombiner.add("\n Super               $20.00");
                    };
                }
            };
            sizeComboBox.addActionListener(sizeListener);
            sizePanel.add(sizeComboBox);
            mainPanel.add(sizePanel);

        //TOPPING SELECT
        JPanel toppingPanel= new JPanel();
        Box toppingBox = new Box(BoxLayout.Y_AXIS);
        JCheckBox pestoCheck =new JCheckBox("Pesto Sauce");
        JCheckBox alfredoCheck =new JCheckBox("Alfredo Sauce");
        JCheckBox mushroomCheck =new JCheckBox("Mushrooms");
        JCheckBox onionCheck= new JCheckBox("Onions");
        JCheckBox beefCheck= new JCheckBox("Beef");
        JCheckBox ricottaCheck= new JCheckBox("Ricotta Cheese");

        toppingBox.add(pestoCheck);
        toppingBox.add(alfredoCheck);
        toppingBox.add(mushroomCheck);
        toppingBox.add(onionCheck);
        toppingBox.add(beefCheck);
        toppingBox.add(ricottaCheck);

        toppingPanel.add(toppingBox);
        mainPanel.add(toppingPanel);

        //RECEIPT DISPLAY
            JPanel receiptPanel = new JPanel();
            JTextArea pizzaReceipt = new JTextArea();
            JScrollPane receiptHolder = new JScrollPane(pizzaReceipt);
            pizzaReceipt.setPreferredSize(receiptSize);
            receiptPanel.add(receiptHolder);
            mainPanel.add(receiptPanel);


        //ORDER CONTROL
        Box pizzaOrderControl =new Box(BoxLayout.Y_AXIS);
        JButton pizzaQuitter = new JButton("Quit");
        ActionListener quitListener =  new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(pizzaQuitter,"Are you sure you want to quit?");
                System.exit(0 );
            }
        };
        pizzaQuitter.addActionListener(quitListener);
        JButton pizzaClearer = new JButton("Clear");
        ActionListener clearListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pizzaReceipt.setText(null);
                currentPizzaPrice.setCost(0.0);
                pizzaStringCombiner.clear();
            }

        };
        pizzaClearer.addActionListener(clearListener);
        JButton pizzaOrderer = new JButton("Order");
            ActionListener orderListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                     if (ricottaCheck.isSelected()){pizzaStringCombiner.add("\n Ricotta             $1.00");
                     currentPizzaPrice.setCost(currentPizzaPrice.getCost()+1.00);}
                     if (pestoCheck.isSelected()){pizzaStringCombiner.add("\n Pesto              $1.00");
                     currentPizzaPrice.setCost(currentPizzaPrice.getCost()+1.00);}
                     if (alfredoCheck.isSelected()){pizzaStringCombiner.add("\n Alfredo           $1.00");
                     currentPizzaPrice.setCost(currentPizzaPrice.getCost()+1.00);}
                     if (beefCheck.isSelected()){pizzaStringCombiner.add("\n Beef                $1.00");
                     currentPizzaPrice.setCost(currentPizzaPrice.getCost()+1.00);}
                     if (mushroomCheck.isSelected()){pizzaStringCombiner.add("\n Mushrooms       $1.00");
                     currentPizzaPrice.setCost(currentPizzaPrice.getCost()+1.00);}

                    int indexNumber=0;
                    String currentReceiptItem;
                    while (indexNumber<=pizzaStringCombiner.size()){
                        currentReceiptItem= String.valueOf(pizzaStringCombiner.get(indexNumber));
                       pizzaReceipt.append(currentReceiptItem);
                       indexNumber++;

                    };
                    pizzaReceipt.append("\n TOTAL: $" +currentPizzaPrice);

                }
            };
        pizzaOrderer.addActionListener(orderListener);
        pizzaOrderControl.add(pizzaQuitter);
        pizzaOrderControl.add(pizzaClearer);
        pizzaOrderControl.add(pizzaOrderer);
        mainPanel.add(pizzaOrderControl);

        //CONFIRMATION MESSAGE

        }
    }
