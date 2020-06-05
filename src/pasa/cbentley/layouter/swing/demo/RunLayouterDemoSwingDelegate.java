/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.demo;

import java.awt.Color;

import javax.swing.JPanel;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.ctx.ConfigUSettable;
import pasa.cbentley.core.src4.ctx.IConfigU;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.layouter.src4.engine.LayoutWillListenerAdapter;
import pasa.cbentley.layouter.src4.interfaces.ILayoutDelegate;
import pasa.cbentley.layouter.src4.interfaces.ILayoutable;
import pasa.cbentley.layouter.src4.tech.ITechLayout;
import pasa.cbentley.layouter.swing.engine.JPanelLayoutable;
import pasa.cbentley.layouter.swing.engine.LayoutableAdapterForJComponent;

public class RunLayouterDemoSwingDelegate extends RunLayouterDemoSwingAbstract {

   public static void main(String[] args) {
      //configuration of the code contexts
      ConfigUSettable configU = new ConfigUSettable();
      configU.ToStringSetUsingClassLinks(true);

      RunLayouterDemoSwingDelegate runner = new RunLayouterDemoSwingDelegate(configU);

      //start inside the swing runner framework
      runner.run();

   }

   private LayoutableAdapterForJComponent panelBlue;

   private LayoutableAdapterForJComponent panelGreen;

   private LayoutableAdapterForJComponent panelOrange;

   private LayoutableAdapterForJComponent panelRed;

   private LayoutableAdapterForJComponent panelWhite;

   private LayoutableAdapterForJComponent panelYellow;

   public RunLayouterDemoSwingDelegate(IConfigU configU) {
      super(configU);

      constructHelpers();

   }

   public void buildDemo(JPanelLayoutable panel) {

      ILayoutDelegate delegate = new ILayoutDelegate() {

         public ILayoutable getDelegateSizer(ByteObject sizer, ILayoutable layoutable, int ctx) {
            if (ctx == ITechLayout.CTX_1_WIDTH) {
               //when computing
            }
            if (layoutable == panelOrange) {

            } else if (layoutable == panelBlue) {

            }
            // TODO Auto-generated method stub
            return null;
         }

         public int getDelegateSizeWidth(ByteObject sizer, ILayoutable layoutable) {
            // TODO Auto-generated method stub
            return 0;
         }

         public int getDelegateSizeHeight(ByteObject sizer, ILayoutable layoutable) {
            // TODO Auto-generated method stub
            return 0;
         }

         public int getDelegatePozerY(ByteObject pozer, ILayoutable layoutable) {
            // TODO Auto-generated method stub
            return 0;
         }

         public int getDelegatePozerX(ByteObject pozer, ILayoutable layoutable) {
            // TODO Auto-generated method stub
            return 0;
         }

         public ILayoutable getDelegateEtalonePozer(ByteObject pozer, ILayoutable layoutable) {
            // TODO Auto-generated method stub
            return null;
         }

         public ILayoutable getDelegateEtaloneSizer(ByteObject sizer, ILayoutable layoutable) {
            // TODO Auto-generated method stub
            return null;
         }

         public String toString1Line() {
            // TODO Auto-generated method stub
            return null;
         }

         public void toString(Dctx dc) {
            // TODO Auto-generated method stub
            
         }

         public void toString1Line(Dctx dc) {
            // TODO Auto-generated method stub
            
         }

         public UCtx toStringGetUCtx() {
            // TODO Auto-generated method stub
            return null;
         }
      };

      createOrange(panel, delegate);
      createBlue(panel);
      createGreen(panel);
      createRed(panel);
      createYellow(panel);
      createWhite(panel);

      //add listener for computations
      slc.toStringSetDebugBreaks(new LayoutWillListenerAdapter() {

         public void layoutWillComputePositions(ILayoutable layoutable) {
            if (layoutable == panelOrange) {
               isBreak = true;
            }
         }

         public void layoutWillComputePositionX(ILayoutable layoutable) {

         }

         public void layoutWillComputePositionY(ILayoutable layoutable) {

         }

         public void layoutWillComputeSizes(ILayoutable layoutable) {

         }

         public void layoutWillComputeSizeH(ILayoutable layoutable) {
         }

         public void layoutWillComputeSizeW(ILayoutable layoutable) {
            if (layoutable == panelOrange) {
               isBreak = true;
            }
         }
      });

   }

   private void createBlue(JPanelLayoutable root) {
      JPanel panel = new JPanel();
      panel.setName("panelBlue");
      panel.setBackground(Color.BLUE);
      panelBlue = root.addLayoutable(panel);
   }

   private void createGreen(JPanelLayoutable root) {
      JPanel panel = new JPanel();
      panel.setName("panelGreen");
      panel.setBackground(Color.GREEN);

      panelGreen = root.addLayoutable(panel);
   }

   private void createOrange(JPanelLayoutable root, ILayoutDelegate delegate) {
      JPanel panel = new JPanel();
      panel.setName("panelOrange");
      panel.setBackground(Color.ORANGE);
      //first added is on the bottom
      panelOrange = root.addLayoutable(panel);

   }

   private void createRed(JPanelLayoutable root) {
      JPanel panel = new JPanel();
      panel.setName("panelYellow");
      panel.setBackground(Color.red);
      panelRed = root.addLayoutable(panel);
   }

   private void createWhite(JPanelLayoutable root) {
      JPanel panel = new JPanel();
      panel.setName("panelWhite");
      panel.setBackground(Color.WHITE);
      panelWhite = root.addLayoutable(panel);
   }

   private void createYellow(JPanelLayoutable root) {
      JPanel panel = new JPanel();
      panel.setName("panelYellow");
      panel.setBackground(Color.YELLOW);
      panelYellow = root.addLayoutable(panel);
   }

}
