/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.demo;

import java.awt.Color;

import javax.swing.JPanel;

import pasa.cbentley.core.src4.ctx.ConfigUSettable;
import pasa.cbentley.core.src4.ctx.IConfigU;
import pasa.cbentley.layouter.src4.engine.LayoutWillListenerAdapter;
import pasa.cbentley.layouter.src4.interfaces.ILayoutable;
import pasa.cbentley.layouter.swing.engine.JPanelLayoutable;
import pasa.cbentley.layouter.swing.engine.LayoutableAdapterForJComponent;

public class RunLayouterDemoSwingRatios extends RunLayouterDemoSwingAbstract {

   private JPanel               panelCenterOrange;

   private LayoutableAdapterForJComponent panelCenterOrangeLayout;

   public RunLayouterDemoSwingRatios(IConfigU configU) {
      super(configU);

      constructHelpers();

   }

   private void createBlue(JPanelLayoutable root, ILayoutable panelCenterOrangeLayout) {
      JPanel panel = new JPanel();
      panel.setName("panelBlue");
      panel.setBackground(Color.BLUE);

      LayoutableAdapterForJComponent layoutable = root.addLayoutable(panel);
      layoutable.getLay().layPoz_MidXToStart_Of(panelCenterOrangeLayout);
      layoutable.getLay().layPoz_MidYToTop_Of(panelCenterOrangeLayout);

      layoutable.getLay().laySiz_W_Ratio100Of(panelCenterOrangeLayout, 50);
      layoutable.getLay().laySiz_H_Ratio100Of(panelCenterOrangeLayout, 30);
   }

   private void createGreen(JPanelLayoutable root, ILayoutable panelCenterOrangeLayout) {
      JPanel panel = new JPanel();
      panel.setName("panelGreen");
      panel.setBackground(Color.GREEN);

      LayoutableAdapterForJComponent layoutable = root.addLayoutable(panel);
      layoutable.getLay().layPoz_MidXToEnd_Of(panelCenterOrangeLayout);
      layoutable.getLay().layPoz_MidYToBot_Of(panelCenterOrangeLayout);

      layoutable.getLay().laySiz_W_Ratio100Of(panelCenterOrangeLayout, 30);
      layoutable.getLay().laySiz_H_Ratio100Of(panelCenterOrangeLayout, 60);
   }

   private void createRed(JPanelLayoutable root, ILayoutable panelCenterOrangeLayout) {
      JPanel panel = new JPanel();
      panel.setName("panelYellow");
      panel.setBackground(Color.red);

      LayoutableAdapterForJComponent layoutable = root.addLayoutable(panel);
      layoutable.getLay().layPoz_MidXToEnd_Of(panelCenterOrangeLayout);
      layoutable.getLay().layPoz_MidYToTop_Of(panelCenterOrangeLayout);

      layoutable.getLay().laySiz_W_Ratio100Of(panelCenterOrangeLayout, 40);
      layoutable.getLay().laySiz_H_Ratio100Of(panelCenterOrangeLayout, 40);
   }

   private void createYellow(JPanelLayoutable root, ILayoutable panelCenterOrangeLayout) {
      JPanel panel = new JPanel();
      panel.setName("panelYellow");
      panel.setBackground(Color.YELLOW);

      LayoutableAdapterForJComponent layoutable = root.addLayoutable(panel);
      layoutable.getLay().layPoz_MidXToStart_Of(panelCenterOrangeLayout);
      layoutable.getLay().layPoz_MidYToBot_Of(panelCenterOrangeLayout);

      layoutable.getLay().laySiz_W_Ratio100Of(panelCenterOrangeLayout, 60);
      layoutable.getLay().laySiz_H_Ratio100Of(panelCenterOrangeLayout, 30);
   }

   private void createWhite(JPanelLayoutable root, ILayoutable panelCenterOrangeLayout) {
      JPanel panel = new JPanel();
      panel.setName("panelWhite");
      panel.setBackground(Color.WHITE);

      LayoutableAdapterForJComponent layoutable = root.addLayoutable(panel);
      layoutable.getLay().layPoz_MidXToMid_Of(panelCenterOrangeLayout);
      layoutable.getLay().layPoz_MidYToMid_Of(panelCenterOrangeLayout);

      layoutable.getLay().laySiz_W_Ratio100Of(panelCenterOrangeLayout, 10);
      layoutable.getLay().laySiz_H_Ratio100Of(panelCenterOrangeLayout, 10);
   }

   public void buildDemo(JPanelLayoutable panel) {
      panelCenterOrange = new JPanel();
      panelCenterOrange.setName("panelCenter");
      panelCenterOrange.setBackground(Color.ORANGE);
      //first added is on the bottom
      panelCenterOrangeLayout = panel.addLayoutable(panelCenterOrange);
      panelCenterOrangeLayout.getLay().setPozerCorner1(midCenterParent);
      panelCenterOrangeLayout.getLay().setSizerH(sizerRatio50);
      panelCenterOrangeLayout.getLay().setSizerW(sizerRatio30);

      createBlue(panel, panelCenterOrangeLayout);
      createGreen(panel, panelCenterOrangeLayout);
      createRed(panel, panelCenterOrangeLayout);
      createYellow(panel, panelCenterOrangeLayout);
      createWhite(panel, panelCenterOrangeLayout);

      //add listener for computations
      slc.toStringSetDebugBreaks(new LayoutWillListenerAdapter() {

         public void layoutWillComputeSizeW(ILayoutable layoutable) {
            if (layoutable == panelCenterOrange) {
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
            if (layoutable == panelCenterOrange) {
               isBreak = true;
            }
         }
      });

   }

   public static void main(String[] args) {
      //configuration of the code contexts
      ConfigUSettable configU = new ConfigUSettable();
      configU.ToStringSetUsingClassLinks(true);

      RunLayouterDemoSwingRatios runner = new RunLayouterDemoSwingRatios(configU);

      //start inside the swing runner framework
      runner.run();

   }

}
