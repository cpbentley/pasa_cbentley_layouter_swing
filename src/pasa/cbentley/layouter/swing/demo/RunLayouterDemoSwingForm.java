/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.demo;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import pasa.cbentley.core.src4.ctx.ConfigUSettable;
import pasa.cbentley.core.src4.ctx.IConfigU;
import pasa.cbentley.layouter.src4.engine.LayoutWillListenerAdapter;
import pasa.cbentley.layouter.src4.engine.LayoutableGhost;
import pasa.cbentley.layouter.src4.interfaces.ILayoutable;
import pasa.cbentley.layouter.swing.engine.ConfiguratorForSwing;
import pasa.cbentley.layouter.swing.engine.JPanelLayoutable;
import pasa.cbentley.layouter.swing.engine.LayoutableChainSwing;

public class RunLayouterDemoSwingForm extends RunLayouterDemoSwingAbstract {

   public RunLayouterDemoSwingForm(IConfigU configU) {
      super(configU);

      constructHelpers();

   }

   public void buildDemo(JPanelLayoutable panel) {
      buildDemo(panel.getConfigurator());
   }

   public void buildDemo(ConfiguratorForSwing panel) {

      ILayoutable parent = panel.getPanel();

      //create a chain
      //      JButton but1 = new JButton("1");
      //      JButton but2 = new JButton("2");
      //      JButton but3 = new JButton("3");
      //
      //      //vertical alignment of top,mid,bot, taking preferred size of the elements?
      //      ChainSwing chainTop = panel.createChainHorizontal();
      //
      //      //
      //      chainTop.appendLast(but1);
      //      chainTop.appendLast(but2);
      //      chainTop.appendLast(but3);
      //
      //      chainTop.getLay().layPozStartTopEndOfParent();
      //      chainTop.getLay().laySizePreferred();

      //create an invisible component to be used as a delimiter
      LayoutableGhost rule40 = panel.createGhostLineVertical(40);
      rule40.toStringSetDebugName("rule vertical 40%");
      LayoutableGhost rule10 = panel.createGhostLineVertical(10);
      rule10.toStringSetDebugName("rule vertical 10%");
      LayoutableGhost rule80 = panel.createGhostLineVertical(80);
      rule80.toStringSetDebugName("rule vertical 80%");

      JLabel labelName = new JLabel("Name:");
      ILayoutable labelNameLayoutable = panel.addLayoutableStartEndTopInsidePref(labelName, rule10, rule40, parent);

      JTextField txtName = new JTextField(40);
      panel.addStartEndTopTopBotBot(txtName, rule40, rule80, labelNameLayoutable);

      JButton butSend = new JButton("Send Message");
      ILayoutable butSendLayoutable = panel.addLayoutableStartBot(butSend, rule40, rule80, parent);

      JTextArea chat = new JTextArea();
      panel.addLayoutableStartEndTopBot(chat, rule10, rule80, labelNameLayoutable, butSendLayoutable);

      JButton butOver = new JButton("Send Message");
      //ILayoutable butOverLayoutable = panel.addLayoutableInsideStartEndTopBot(butOver, parent, parent, parent, parent);

      //#debug
      toDLog().pInit("msg", panel, RunLayouterDemoSwingForm.class, "buildDemo", LVL_08_INFO, false);

      //add listener for computations
      slc.toStringSetDebugBreaks(new LayoutWillListenerAdapter() {

         public void layoutWillComputeSizeW(ILayoutable layoutable) {
            if (layoutable == labelNameLayoutable) {
               isBreak = true;
            }
         }

         public void layoutWillComputeSizeH(ILayoutable layoutable) {
         }

         public void layoutWillComputeSizes(ILayoutable layoutable) {

         }

         public void layoutWillComputePositionY(ILayoutable layoutable) {

         }

         public void layoutWillComputePositionX(ILayoutable layoutable) {

         }

         public void layoutWillComputePositions(ILayoutable layoutable) {
            if (layoutable == labelNameLayoutable) {
               isBreak = true;
            }
         }
      });

   }

   public static void main(String[] args) {
      //configuration of the code contexts
      ConfigUSettable configU = new ConfigUSettable();
      configU.ToStringSetUsingClassLinks(true);

      RunLayouterDemoSwingForm runner = new RunLayouterDemoSwingForm(configU);

      //start inside the swing runner framework
      runner.run();

   }

}
