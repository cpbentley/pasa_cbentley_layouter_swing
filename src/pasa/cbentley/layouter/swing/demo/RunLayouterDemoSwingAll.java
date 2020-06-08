/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.demo;

import javax.swing.JButton;

import pasa.cbentley.core.src4.ctx.ConfigUSettable;
import pasa.cbentley.core.src4.ctx.IConfigU;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.interfaces.C;
import pasa.cbentley.core.src4.logging.ILogConfigurator;
import pasa.cbentley.layouter.swing.ctx.ConfigSwingDemoLayouter;
import pasa.cbentley.layouter.swing.ctx.IConfigSwingLayouter;
import pasa.cbentley.layouter.swing.engine.ConfiguratorForSwing;
import pasa.cbentley.layouter.swing.engine.JPanelLayoutable;
import pasa.cbentley.layouter.swing.engine.LayoutableChainSwing;
import pasa.cbentley.swing.ctx.IConfigSwing;
import pasa.cbentley.swing.logging.LogConfiguratorSwingFrames;
import pasa.cbentley.swing.window.CBentleyFrame;

/**
 * Intercepts the {@link CBentleyFrame} and sets the class name relevant suffix as title
 * @author Charles Bentley
 *
 */
public class RunLayouterDemoSwingAll extends RunLayouterDemoSwingAbstract {

   private LayouterDemoFactory factory;

   public RunLayouterDemoSwingAll(IConfigU configU) {
      super(configU);

      constructHelpers();

   }

   public void constructHelpers() {
      super.constructHelpers();
      factory = new LayouterDemoFactory(slc);
   }

   public void create(Class c, String text, LayoutableChainSwing chain) {
      JButton but = new JButton(text);

      LayouterDemoButtonAction action = new LayouterDemoButtonAction(slc, factory, c);
      action.setText(text);
      
      but.addActionListener(action);

      //add the button in a flow like manner wrapper

      chain.appendLast(but);
   }

   public void buildDemo(JPanelLayoutable panel) {

      //create chains
      ConfiguratorForSwing configurator = panel.getConfigurator();

      LayoutableChainSwing chainVertical = configurator.createChainVertical();
      chainVertical.setChainElementAlignment(C.LOGIC_1_TOP_LEFT);
      chainVertical.toStringSetDebugName("Chain Vertical");

      chainVertical.layout().layPoz_Inside_OfParent();

      LayoutableChainSwing chainH1 = configurator.createChainHorizontal();
      //#debug
      chainH1.toStringSetDebugName("Chain 1");

      LayoutableChainSwing chainH2 = configurator.createChainHorizontal();
      //#debug
      chainH2.toStringSetDebugName("Chain 2");

      LayoutableChainSwing chainH3 = configurator.createChainHorizontal();
      //#debug
      chainH3.toStringSetDebugName("Chain 3");

      chainVertical.appendLast(chainH1);
      chainVertical.appendLast(chainH2);
      chainVertical.appendLast(chainH3);

      create(RunLayouterDemoSwingButtons.class, "Buttons", chainH1);
      create(RunLayouterDemoSwingButtonsDrag.class, "Buttons Drag", chainH1);

      create(RunLayouterDemoSwingGhostRule.class, "Ghost Rules", chainH2);
      create(RunLayouterDemoSwingChain.class, "Chains", chainH2);
      create(RunLayouterDemoSwingForm.class, "Form", chainH2);
      create(RunLayouterDemoSwingPozeePoint.class, "Pozee Point", chainH2);
      create(RunLayouterDemoSwingPozeePoint.class, "Delegate", chainH2);

      create(RunLayouterDemoSwingRatios.class, "Ratios", chainH3);
      create(RunLayouterDemoSwingRatiosMany.class, "Ratios Many", chainH3);

   }

   public ILogConfigurator toStringGetLoggingConfig() {
      return new LogConfiguratorSwingFrames();
   }

   public IConfigSwing createConfigSwingLayouter(UCtx uc) {
      IConfigSwing configSwing = new ConfigSwingDemoLayouter(uc);
      return configSwing;
   }

   public static void main(String[] args) {
      //configuration of the code contexts
      ConfigUSettable configU = new ConfigUSettable();
      configU.ToStringSetUsingClassLinks(true);

      RunLayouterDemoSwingAll runner = new RunLayouterDemoSwingAll(configU);

      //start inside the swing runner framework
      runner.run();

   }

}
