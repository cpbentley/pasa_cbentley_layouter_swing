/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.demo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import pasa.cbentley.core.src4.ctx.ConfigUSettable;
import pasa.cbentley.core.src4.ctx.IConfigU;
import pasa.cbentley.core.src4.interfaces.C;
import pasa.cbentley.layouter.src4.engine.LayoutWillListenerAdapter;
import pasa.cbentley.layouter.src4.interfaces.ILayoutable;
import pasa.cbentley.layouter.swing.demo.actions.ActionChainAlignmentIterate;
import pasa.cbentley.layouter.swing.engine.ConfiguratorForSwing;
import pasa.cbentley.layouter.swing.engine.JPanelLayoutable;
import pasa.cbentley.layouter.swing.engine.LayoutableAdapterForJComponent;
import pasa.cbentley.layouter.swing.engine.LayoutableChainSwing;

public class RunLayouterDemoSwingChain extends RunLayouterDemoSwingAbstract {

   public RunLayouterDemoSwingChain(IConfigU configU) {
      super(configU);

      constructHelpers();

   }

   public void buildDemo(JPanelLayoutable panel) {
      buildDemo(panel.getConfigurator());
   }

   public void buildDemo(ConfiguratorForSwing configurator) {

      ILayoutable parent = configurator.getPanel();

      //create a chain
      JButton but1 = new JButton("First Button of top Chain");
      JButton but2 = new JButton("Chain Button H2");
      JButton but3 = new JButton("Chain Button H3");

      //vertical alignment of top,mid,bot, taking preferred size of the elements?
      LayoutableChainSwing chainTop = configurator.createChainHorizontal();
      //#debug
      chainTop.toStringSetDebugName("Chain Horizontal Top");
      //
      chainTop.appendLast(but1);
      chainTop.appendLast(but2);
      chainTop.appendLast(but3);

      //chainTop.getLay().layPoz_Inside_StartTopEnd_OfParent_PrefH();
      chainTop.getLay().layPoz_StartToStart_OfParent();
      chainTop.getLay().layPoz_TopToTop_OfParent();

      LayoutableChainSwing chainTop1 = configurator.createChainHorizontal();
      //#debug
      chainTop1.toStringSetDebugName("Chain Horizontal 1 Element");
      
      JButton butAlone = new JButton("Chain Below Top Chain with only 1 Button");
      chainTop1.appendLast(butAlone);

      chainTop1.layout().layPoz_StartToStart_OfParent();
      chainTop1.layout().layPoz_TopToBot_Of(chainTop);

      
      LayoutableChainSwing chainMiddleRight = configurator.createChainHorizontal();
     
      chainMiddleRight.getLay().layPoz_EndToEnd_Parent();
      chainMiddleRight.getLay().layPoz_MidYToMid_OfParent();

   
      LayoutableAdapterForJComponent panelBlue = configurator.createPanel("panelBlue", Color.BLUE);
      
      panelBlue.lay().laySiz_WH_Ratio100OfParent(20,30);
      
      JButton butRightMid = new JButton("Chain Middle Right");
      JButton butRightMid2 = new JButton("Another But");
      
      ActionChainAlignmentIterate action = new ActionChainAlignmentIterate(slc,panel,chainMiddleRight);
      butRightMid.addActionListener(action);
      butRightMid2.addActionListener(action);
      
      chainMiddleRight.setChainElementAlignment(C.LOGIC_3_BOTTOM_RIGHT);
      
      chainMiddleRight.appendLast(panelBlue);
      chainMiddleRight.appendLast(butRightMid);
      chainMiddleRight.appendLast(butRightMid2);
      
      chainMiddleRight.setAlignReference(panelBlue);
      
      
      LayoutableChainSwing chainVertical = configurator.createChainVertical();
      //#debug
      chainVertical.toStringSetDebugName("Chain Vertical 3");

      
      JButton butV1 = new JButton("Button V1");
      JButton butV2 = new JButton("Chain Button V2");
      JButton butV3 = new JButton("Chain Button V3");

      //yet another example why Anonymous class is hardly ever a good choice.
      ActionChainAlignmentIterate actionV = new ActionChainAlignmentIterate(slc,panel,chainVertical);
      butV1.addActionListener(actionV);
      butV2.addActionListener(actionV); //we can easily reuse. its clear and easy to read.
      butV3.addActionListener(actionV); //we can easily reuse. its clear and easy to read.

      chainVertical.appendLast(butV1);
      chainVertical.appendLast(butV2);
      chainVertical.appendLast(butV3);

      chainVertical.lay().layPoz_StartToStart_OfParent();
      chainVertical.layout().layPoz_BotToBot_OfParent();

      LayoutableChainSwing chainVertical1 = configurator.createChainVertical();
      chainVertical1.toStringSetDebugName("Chain Vertical 1");
      chainVertical1.lay().layPoz_EndToEnd_Parent();
      chainVertical1.lay().layPoz_BotToBot_OfParent();

      JButton butVAlone = new JButton("Chain Button Alone V");
      chainVertical1.appendLast(butVAlone);

     
      
      JButton butDebug = new JButton("toString Panel");
      //this is a badlazy use case of an Anonymous class. Should be a class object. 
      //Readability suffers.. The Action class name would give away on the first line what is happening
      butDebug.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            //#debug
            toDLog().pAlways("", parent, RunLayouterDemoSwingChain.class, "actionPerformed", LVL_08_INFO, false);
         }
      });
      
      LayoutableAdapterForJComponent butDebugLay = configurator.createLayoutableFromSwingPref(butDebug);
      butDebugLay.getLay().layPoz_MidXToMid_OfParent();
      butDebugLay.getLay().layPoz_MidYToMid_OfParent();
      
      //#mdebug
      //this is one of those rare case when an Anonymous class makes sense.
      //we want to manipulate the state at will for setting the breakpoints
      slc.toStringSetDebugBreaks(new LayoutWillListenerAdapter() {

         public void layoutWillComputeSizeW(ILayoutable layoutable) {
            if (layoutable == chainTop) {
               isBreak = true;
            }
            if (layoutable == chainVertical) {
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
            if (layoutable == chainTop) {
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

      RunLayouterDemoSwingChain runner = new RunLayouterDemoSwingChain(configU);

      //start inside the swing runner framework
      runner.run();

   }

}
