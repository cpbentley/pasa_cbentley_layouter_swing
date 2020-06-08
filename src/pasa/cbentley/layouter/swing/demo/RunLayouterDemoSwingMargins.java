/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.demo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.ctx.ConfigUSettable;
import pasa.cbentley.core.src4.ctx.IConfigU;
import pasa.cbentley.core.src4.interfaces.C;
import pasa.cbentley.layouter.src4.engine.LayoutWillListenerAdapter;
import pasa.cbentley.layouter.src4.engine.LayoutableGhost;
import pasa.cbentley.layouter.src4.interfaces.ILayoutable;
import pasa.cbentley.layouter.swing.demo.actions.ActionChainAlignmentIterate;
import pasa.cbentley.layouter.swing.engine.ConfiguratorForSwing;
import pasa.cbentley.layouter.swing.engine.JComponentLayoutable;
import pasa.cbentley.layouter.swing.engine.JPanelLayoutable;
import pasa.cbentley.layouter.swing.engine.LayEngineSwing;
import pasa.cbentley.layouter.swing.engine.LayoutableAdapterForJComponent;
import pasa.cbentley.layouter.swing.engine.LayoutableChainSwing;

public class RunLayouterDemoSwingMargins extends RunLayouterDemoSwingAbstract {

   public RunLayouterDemoSwingMargins(IConfigU configU) {
      super(configU);

      constructHelpers();

   }

   public void buildDemo(JPanelLayoutable panel) {
      buildDemo(panel.getConfigurator());
   }

   public void buildDemo(ConfiguratorForSwing configurator) {

      ILayoutable parent = configurator.getPanel();

      //first let's use rules for margins
      //create an invisible component to be used as a delimiter
      LayoutableGhost ruleH10 = configurator.createGhostLineHorizontal(10);
      ruleH10.toStringSetDebugName("rule horizontal 10%");

      LayoutableGhost ruleH90 = configurator.createGhostLineHorizontal(90);
      ruleH90.toStringSetDebugName("rule horizontal 90%");

      LayoutableGhost ruleV10 = configurator.createGhostLineVertical(10);
      ruleV10.toStringSetDebugName("rule vertical 10%");

      LayoutableGhost ruleV90 = configurator.createGhostLineVertical(90);
      ruleV90.toStringSetDebugName("rule vertical 90%");

      JComponentLayoutable bgBlack = new JComponentLayoutable(slc);
      bgBlack.setName("bgBlack");
      bgBlack.setBackground(new Color(0, 0, 10, 255));

      configurator.addInsideStartEndTopBot(bgBlack, ruleV10, ruleV90, ruleH10, ruleH90);

      //shows a semi transparent color over the button with a margin of 10 percent the contextual size
      JComponentLayoutable trans = new JComponentLayoutable(slc);
      trans.setName("trans");
      trans.setBackground(new Color(255, 50, 10, 128));
      //size is defined by anchor points
      configurator.addInsideStartEndTopBot_Margin(trans, bgBlack, sizerRatio5);

      JComponentLayoutable transPadding = new JComponentLayoutable(slc);
      transPadding.setName("transPadding");
      transPadding.setBackground(new Color(0, 50, 255, 220));
      //size is defined by anchor points
      configurator.addInsideStartEndTopBot_Padding(transPadding, bgBlack, sizerRatio10);

      
      //#mdebug
      //this is one of those rare case when an Anonymous class makes sense.
      //we want to manipulate the state at will for setting the breakpoints
      slc.toStringSetDebugBreaks(new LayoutWillListenerAdapter() {

         public void layoutWillComputeSizeW(ILayoutable layoutable) {
            if (layoutable == trans) {
               isBreak = true;
            }
            if (layoutable == ruleV10) {
               isBreak = true;
            }
         }

         public void layoutWillComputeSizeH(ILayoutable layoutable) {
         }

         public void layoutWillComputeSizes(ILayoutable layoutable) {
         }

         public void layoutWillComputePositionY(ILayoutable layoutable) {
            if (layoutable == ruleH10) {
               isBreak = true;
            }
         }

         public void layoutWillComputePositionX(ILayoutable layoutable) {
            if (layoutable == ruleV10) {
               isBreak = true;
            }
         }

         public void layoutWillComputePositions(ILayoutable layoutable) {
            if (layoutable == ruleH10) {
               isBreak = true;
            }
         }
      });
      //#enddebug
   }

   public static void main(String[] args) {
      //configuration of the code contexts
      ConfigUSettable configU = new ConfigUSettable();
      configU.ToStringSetUsingClassLinks(true);

      RunLayouterDemoSwingMargins runner = new RunLayouterDemoSwingMargins(configU);

      //start inside the swing runner framework
      runner.run();

   }

}
