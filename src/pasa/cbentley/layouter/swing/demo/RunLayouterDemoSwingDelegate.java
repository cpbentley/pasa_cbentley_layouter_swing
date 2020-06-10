/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.demo;

import java.awt.Color;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.ctx.ConfigUSettable;
import pasa.cbentley.core.src4.ctx.IConfigU;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.layouter.src4.engine.Area2DConfigurator;
import pasa.cbentley.layouter.src4.engine.LayoutDelegateAdapter;
import pasa.cbentley.layouter.src4.engine.LayoutWillListenerAdapter;
import pasa.cbentley.layouter.src4.interfaces.ILayoutDelegate;
import pasa.cbentley.layouter.src4.interfaces.ILayoutable;
import pasa.cbentley.layouter.src4.tech.ITechLayout;
import pasa.cbentley.layouter.swing.engine.ConfiguratorForSwing;
import pasa.cbentley.layouter.swing.engine.DragPozerListener;
import pasa.cbentley.layouter.swing.engine.JPanelLayoutable;
import pasa.cbentley.layouter.swing.engine.KeyListenerF4DebugStringable;
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

   private Random                         rnd;

   public RunLayouterDemoSwingDelegate(IConfigU configU) {
      super(configU);

      constructHelpers();

      rnd = slc.getUCtx().getRandom();
   }

   public void buildDemo(JPanelLayoutable panel) {

      ConfiguratorForSwing configurator = panel.getConfigurator();

      ILayoutDelegate delegate = new LayoutDelegateAdapter(slc) {

         public int getDelegateSizeHeight(ByteObject sizer, ILayoutable layoutable) {
            if (layoutable == panelOrange) {
               int h = slc.getUCtx().getRandom().nextInt(300) + 10;
               return h;
            } else if (layoutable == panelGreen) {
               int currentH = layoutable.getSizeDrawnHeight();
               int multi = 1;
               if (currentH > 300) {
                  multi = -1;
               }
               int h = currentH + (multi * rnd.nextInt(300)) + 10;
               return h;
            } else {
               return rnd.nextInt(100) + 20;
            }
         }

         public int getDelegateSizeWidth(ByteObject sizer, ILayoutable layoutable) {
            if (layoutable == panelOrange) {
               int h = rnd.nextInt(300) + 10;
               return h;
            } else if (layoutable == panelGreen) {
               int currentWidth = layoutable.getSizeDrawnWidth();
               int multi = 1;
               if (currentWidth > 300) {
                  multi = -1;
               }
               int w = currentWidth + (multi * rnd.nextInt(300)) + 10;
               return w;
            } else if (layoutable == panelRed) {
               int currentWidth = layoutable.getSizeDrawnWidth();
               int multi = 1;
               if (currentWidth > 300) {
                  multi = -1;
               }
               if (currentWidth < 50) {
                  currentWidth += 50;
               }
               int w = currentWidth + (multi * rnd.nextInt(20));
               return w;
            } else {
               return rnd.nextInt(100) + 20;
            }
         }
      };

      
      createOrange(panel, delegate);

      createBlue(panel, delegate);

      createGreen(panel, delegate);

      createRed(panel, delegate);
      //createYellow(panel);
      //createWhite(panel);

      
      //add button last, so it is drawn last
      JButton buttonMove = new JButton("Move Me!");
      buttonMove.setName("buttonMoveMe");
      LayoutableAdapterForJComponent buttonMoveLay = configurator.createLayoutableFromSwingPref(buttonMove);
      buttonMoveLay.getLay().layPoz_MidXToMid_OfParent();
      buttonMoveLay.getLay().layPoz_MidYToMid_OfParent();

      DragPozerListener listener = new DragPozerListener(slc, buttonMoveLay, panel);

      KeyListenerF4DebugStringable keyLis = new KeyListenerF4DebugStringable(slc, panel);

      buttonMove.addKeyListener(keyLis);
      buttonMove.addMouseListener(listener);
      buttonMove.addMouseMotionListener(listener);
      buttonMove.addMouseWheelListener(listener);

      
      
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

   private void createBlue(JPanelLayoutable root, ILayoutDelegate delegate) {
      JPanel panel = new JPanel();
      panel.setName("panelBlue");
      panel.setBackground(Color.BLUE);
      panelBlue = root.addLayoutable(panel);

      Area2DConfigurator lay = panelBlue.getLay();

      lay.layPoz_TopToTop_OfParent();
      lay.layPoz_EndToEnd_Parent();

      ByteObject sizer = slc.getSizerFactory().getSizerDelegate(delegate);
      lay.setSizerH(sizer);
      lay.setSizerW(sizer);
   }

   private void createGreen(JPanelLayoutable root, ILayoutDelegate delegate) {
      JPanel panel = new JPanel();
      panel.setName("panelGreen");
      panel.setBackground(Color.GREEN);

      panelGreen = root.addLayoutable(panel);

      panelGreen.getLay().layPoz_BotToBot_OfParent();
      panelGreen.getLay().layPoz_EndToEnd_Parent();

      ByteObject sizer = slc.getSizerFactory().getSizerDelegate(delegate);
      panelGreen.getLay().setSizerH(sizer);
      panelGreen.getLay().setSizerW(sizer);
   }

   private void createOrange(JPanelLayoutable root, ILayoutDelegate delegate) {
      JPanel panel = new JPanel();
      panel.setName("panelOrange");
      panel.setBackground(Color.ORANGE);
      //first added is on the bottom
      panelOrange = root.addLayoutable(panel);

      ByteObject pozerAtPixel0Lazy = slc.getPozerFactory().getPozerAtPixel0Lazy();

      panelOrange.getLay().setPozerXStart(pozerAtPixel0Lazy);
      panelOrange.getLay().setPozerYTop(pozerAtPixel0Lazy);

      ByteObject sizer = slc.getSizerFactory().getSizerDelegate(delegate);
      panelOrange.getLay().setSizerH(sizer);
      panelOrange.getLay().setSizerW(sizer);

   }

   private void createRed(JPanelLayoutable root, ILayoutDelegate delegate) {
      JPanel panel = new JPanel();
      panel.setName("panelRed");
      panel.setBackground(Color.red);
      panelRed = root.addLayoutable(panel);

      Area2DConfigurator lay = panelRed.getLay();

      lay.layPoz_StartToStart_OfParent();
      lay.layPoz_BotToBot_OfParent();

      ByteObject sizer = slc.getSizerFactory().getSizerDelegate(delegate);
      lay.setSizerH(sizer);
      lay.setSizerW(sizer);
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
