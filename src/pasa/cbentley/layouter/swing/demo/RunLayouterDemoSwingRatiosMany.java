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
import pasa.cbentley.layouter.src4.engine.PozerFactory;
import pasa.cbentley.layouter.src4.interfaces.ILayoutable;
import pasa.cbentley.layouter.src4.tech.ITechLayout;
import pasa.cbentley.layouter.swing.demo.actions.ActionChainAlignmentIterate;
import pasa.cbentley.layouter.swing.engine.ConfiguratorForSwing;
import pasa.cbentley.layouter.swing.engine.JComponentLayoutable;
import pasa.cbentley.layouter.swing.engine.JPanelLayoutable;
import pasa.cbentley.layouter.swing.engine.LayoutableAdapterForJComponent;
import pasa.cbentley.layouter.swing.engine.LayoutableChainSwing;

public class RunLayouterDemoSwingRatiosMany extends RunLayouterDemoSwingAbstract {

   public RunLayouterDemoSwingRatiosMany(IConfigU configU) {
      super(configU);

      constructHelpers();

   }

   public void buildDemo(JPanelLayoutable panel) {
      buildDemo(panel.getConfigurator());
   }

   public void buildDemo(ConfiguratorForSwing configurator) {

      ILayoutable parent = configurator.getPanel();

      ByteObject sizer45PercentParent = slc.getSizerFactory().getSizerRatio100(45, ITechLayout.ETALON_4_PARENT);
      ByteObject sizer40PercentParent = slc.getSizerFactory().getSizerRatio100(40, ITechLayout.ETALON_4_PARENT);
      ByteObject sizer30PercentParent = slc.getSizerFactory().getSizerRatio100(30, ITechLayout.ETALON_4_PARENT);

      ByteObject sizer10PercentParent = slc.getSizerFactory().getSizerRatio100(10, ITechLayout.ETALON_4_PARENT);
      ByteObject sizer20PercentParent = slc.getSizerFactory().getSizerRatio100(10, ITechLayout.ETALON_4_PARENT);

      
      PozerFactory pozerFac = slc.getFactoryPozer();

      ByteObject centerToCenter = pozerFac.getPozerCenterToCenter();
      
      ByteObject botToBot = pozerFac.getPozerBotToBot();
      ByteObject topToTop = pozerFac.getPozerTopToTop();
      ByteObject leftToLeft = pozerFac.getPozerTopToTop();
      ByteObject rightToRight = pozerFac.getPozerRightToRight();

      ByteObject topToMid = pozerFac.getPozerTopToMid();
      ByteObject botToMid = pozerFac.getPozerBotToMid();
      ByteObject endToMid = botToMid;
      
      JComponentLayoutable white = new JComponentLayoutable(slc);
      white.setName("white");
      white.setBackground(Color.white);
      white.setSizer(sizer30PercentParent, sizer40PercentParent);
      white.setPozer(centerToCenter, centerToCenter);

      
      JComponentLayoutable green = new JComponentLayoutable(slc);
      green.setName("green");
      green.setBackground(Color.GREEN);
      green.setSizer(sizer30PercentParent, sizer40PercentParent);
      green.setPozer(leftToLeft, topToTop);

      JComponentLayoutable yellow = new JComponentLayoutable(slc);
      yellow.setName("yellow");
      yellow.setBackground(Color.yellow);
      yellow.setSizer(sizer30PercentParent, sizer10PercentParent);
      yellow.setPozer(leftToLeft, botToBot);

      
      JComponentLayoutable red = new JComponentLayoutable(slc);
      red.setName("red");
      red.setBackground(Color.RED);
      red.setSizer(sizer20PercentParent, sizer10PercentParent);
      red.setPozer(rightToRight, botToBot);
      
      
      JComponentLayoutable blue = new JComponentLayoutable(slc);
      blue.setName("blue");
      blue.setBackground(Color.BLUE);
      blue.setSizer(sizer30PercentParent, sizer10PercentParent);
      blue.setPozer(rightToRight, topToTop);
      
      JComponentLayoutable grey = new JComponentLayoutable(slc);
      grey.setName("grey");
      grey.setBackground(Color.gray);
      grey.setSizer(sizer10PercentParent, sizer10PercentParent);
      grey.setPozer(centerToCenter, centerToCenter);
    
      
      JComponentLayoutable pink = new JComponentLayoutable(slc);
      pink.setName("pink");
      pink.setBackground(Color.pink);
      pink.setSizer(sizer10PercentParent, sizer40PercentParent);
      pink.setPozer(centerToCenter, botToBot);
    
      JComponentLayoutable cyan = new JComponentLayoutable(slc);
      cyan.setName("cyan");
      cyan.setBackground(Color.CYAN);
      cyan.setSizer(sizer45PercentParent, sizer20PercentParent);
      cyan.setPozer(rightToRight, centerToCenter);
      
      JComponentLayoutable orange = new JComponentLayoutable(slc);
      orange.setName("orange");
      orange.setBackground(Color.orange);
      orange.setSizer(sizer10PercentParent, sizer20PercentParent);
      orange.setPozer(topToMid, botToMid);
    
      JComponentLayoutable magenta = new JComponentLayoutable(slc);
      magenta.setName("magenta");
      magenta.setBackground(Color.magenta);
      magenta.setSizer(sizer20PercentParent, sizer20PercentParent);
      magenta.setPozer(endToMid, topToMid);
    
      
      configurator.addLayoutableOnTop(white);
      configurator.addLayoutableOnTop(green);
      configurator.addLayoutableOnTop(red);
      configurator.addLayoutableOnTop(blue);
      configurator.addLayoutableOnTop(yellow);
      configurator.addLayoutableOnTop(grey);
      configurator.addLayoutableOnTop(cyan);
      configurator.addLayoutableOnTop(pink);
      configurator.addLayoutableOnTop(orange);
      configurator.addLayoutableOnTop(magenta);
      
      //#mdebug
      //this is one of those rare case when an Anonymous class makes sense.
      //we want to manipulate the state at will for setting the breakpoints
      slc.toStringSetDebugBreaks(new LayoutWillListenerAdapter() {

         public void layoutWillComputeSizeW(ILayoutable layoutable) {
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
            if (layoutable == null) {
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

      RunLayouterDemoSwingRatiosMany runner = new RunLayouterDemoSwingRatiosMany(configU);

      //start inside the swing runner framework
      runner.run();

   }

}
