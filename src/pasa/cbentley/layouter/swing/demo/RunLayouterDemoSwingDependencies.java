/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.demo;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
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
import pasa.cbentley.layouter.swing.engine.JComponentLayoutable;
import pasa.cbentley.layouter.swing.engine.JPanelLayoutable;
import pasa.cbentley.layouter.swing.engine.KeyListenerF4DebugStringable;
import pasa.cbentley.layouter.swing.engine.LayoutableAdapterForJComponent;

public class RunLayouterDemoSwingDependencies extends RunLayouterDemoSwingAbstract {

   public static void main(String[] args) {
      //configuration of the code contexts
      ConfigUSettable configU = new ConfigUSettable();
      configU.ToStringSetUsingClassLinks(true);

      RunLayouterDemoSwingDependencies runner = new RunLayouterDemoSwingDependencies(configU);

      //start inside the swing runner framework
      runner.run();

   }

   public RunLayouterDemoSwingDependencies(IConfigU configU) {
      super(configU);

      constructHelpers();

   }

   private JComponentLayoutable create(ConfiguratorForSwing configurator) {
      JComponentLayoutable j3 = new JComponentLayoutable(slc);
      ResizerListener rl = new ResizerListener(slc, j3);
      j3.addMouseListener(rl);
      j3.addMouseMotionListener(rl);
      j3.addMouseWheelListener(rl);
      j3.addKeyListener(rl);
      configurator.addLayoutableOnTop(j3);
      
      return j3;
   }

   public void buildDemo(JPanelLayoutable panel) {

      ConfiguratorForSwing configurator = panel.getConfigurator();

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

      JComponentLayoutable j1 = create(configurator);
      j1.setName("j1");
      j1.setBackground(new Color(40, 210, 100, 128));
      j1.getLay().setSizePixel(80, 10);
      j1.getLay().layPoz_TopToBot_Of(buttonMoveLay);
      j1.getLay().layPoz_StartToEnd_Of(buttonMoveLay);

      JComponentLayoutable j2 = create(configurator);
      j2.setName("j2");
      j2.setBackground(new Color(240, 210, 100, 128));
      Area2DConfigurator lay = j2.getLay();
      lay.setSizePixel(40, 100);
      lay.layPoz_BotToTop_Of(j1);
      lay.layPoz_EndToEnd_Of(j1);

      JComponentLayoutable j3 = create(configurator);
      j3.setName("j3");
      j3.setBackground(new Color(240, 110, 40, 128));
      lay = j3.getLay();
      lay.setSizePixel(180, 15);
      lay.layPoz_TopToTop_Of(j2);
      lay.layPoz_EndToStart_Of(j2);

      JComponentLayoutable j4 = create(configurator);
      j4.setName("j4");
      j4.setBackground(new Color(240, 110, 240, 128));
      lay = j4.getLay();
      lay.setSizePixel(30, 150);
      lay.layPoz_TopToBot_Of(j3);
      lay.layPoz_StartToStart_Of(j3);

      //add listener for computations
      slc.toStringSetDebugBreaks(new LayoutWillListenerAdapter() {

         public void layoutWillComputePositions(ILayoutable layoutable) {
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
         }
      });

   }

}
