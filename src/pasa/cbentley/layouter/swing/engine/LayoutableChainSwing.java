/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.engine;

import javax.swing.JComponent;

import pasa.cbentley.layouter.src4.engine.LayoutableChain;
import pasa.cbentley.layouter.swing.ctx.SwingLayouterCtx;

public class LayoutableChainSwing extends LayoutableChain {

   private JPanelLayoutable     owner;

   protected final SwingLayouterCtx slc;

   /**
    * 
    * @param lac
    * @param owner
    */
   public LayoutableChainSwing(SwingLayouterCtx slc, JPanelLayoutable owner) {
      super(slc);
      this.slc = slc;
      this.owner = owner;
   }

   public void appendFirst(JComponent c) {
      LayoutableAdapterForJComponent adapter = new LayoutableAdapterForJComponent(slc, c);

      super.appendFirst(adapter);

      owner.addLayoutableNoCheck(adapter);
   }

   public void appendLast(JComponent c) {
      LayoutableAdapterForJComponent adapter = new LayoutableAdapterForJComponent(slc, c);
      adapter.getLay().laySiz_Preferred();
      super.appendLast(adapter);

      owner.addLayoutableNoCheck(adapter);
   }

   public void appendLastPrefSize(JComponent c) {
      LayoutableAdapterForJComponent adapter = new LayoutableAdapterForJComponent(slc, c);
      adapter.getLay().laySiz_Preferred();
      super.appendLast(adapter);
      owner.addLayoutableNoCheck(adapter);
   }
}
