/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.demo;

import java.awt.Color;

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

public class RunLayouterDemoSwingGhostRule extends RunLayouterDemoSwingAbstract {

   public RunLayouterDemoSwingGhostRule(IConfigU configU) {
      super(configU);

      constructHelpers();

   }

   public void buildDemo(JPanelLayoutable panel) {
      buildDemo(panel.getConfigurator());
   }

   public void buildDemo(ConfiguratorForSwing panel) {

      ILayoutable parent = panel.getPanel();

      //create an invisible component to be used as a delimiter
      LayoutableGhost ruleV10 = panel.createGhostLineVertical(10);
      ruleV10.toStringSetDebugName("rule vertical 10%");

      
      LayoutableGhost ruleV40 = panel.createGhostLineVertical(40);
      ruleV40.toStringSetDebugName("rule vertical 40%");

      LayoutableGhost ruleV80 = panel.createGhostLineVertical(80);
      ruleV80.toStringSetDebugName("rule vertical 80%");

      LayoutableGhost ruleH10 = panel.createGhostLineHorizontal(10);
      ruleV80.toStringSetDebugName("rule horizontal 10%");

      
      JButton butOver = new JButton("Send Message");
      ILayoutable butOverLayoutable = panel.addLayoutableInsideStartEndTopBot(butOver, ruleV40, ruleV80, ruleH10, parent);

      JLabel labelName = new JLabel("Name:");
      labelName.setBackground(Color.WHITE);
      ILayoutable labelNameLayoutable = panel.addLayoutableStartEndInsideBelow(labelName, ruleV10, ruleV40, ruleH10);

      JTextField txtName = new JTextField(40);
      ILayoutable txtNameLayoutable = panel.addLayoutableStartEndOutsideCenter(txtName, labelNameLayoutable, ruleV40, labelNameLayoutable);

      JButton butBelow = new JButton("Below Name");
      ILayoutable butBelowLayoutable = panel.addLayoutableStartEndInsideBelow(butBelow, parent, ruleV40, labelNameLayoutable);

      
      //#debug
      toDLog().pInit("msg", panel, RunLayouterDemoSwingGhostRule.class, "buildDemo", LVL_08_INFO, false);

      //add listener for computations
      slc.toStringSetDebugBreaks(new LayoutWillListenerAdapter() {

         public void layoutWillComputeSizeW(ILayoutable layoutable) {
            if (layoutable == butOverLayoutable) {
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
            if (layoutable == butOverLayoutable) {
               isBreak = true;
            }
         }
      });

   }
   
   protected void initPostFrameShown() {
      super.initPostFrameShown();
      
      //#debug
      toDLog().pFlow("", panel, RunLayouterDemoSwingGhostRule.class, "main", LVL_05_FINE, false);
      
   }
   public static void main(String[] args) {
      //configuration of the code contexts
      ConfigUSettable configU = new ConfigUSettable();
      configU.ToStringSetUsingClassLinks(true);

      RunLayouterDemoSwingGhostRule runner = new RunLayouterDemoSwingGhostRule(configU);

      //start inside the swing runner framework
      runner.run();

   }

}
