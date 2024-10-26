/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.demo.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pasa.cbentley.core.src4.interfaces.C;
import pasa.cbentley.layouter.swing.ctx.LayouterSwingCtx;
import pasa.cbentley.layouter.swing.engine.JPanelLayoutable;
import pasa.cbentley.layouter.swing.engine.LayoutableChainSwing;

public class ActionChainAlignmentIterate implements ActionListener {

   protected final LayouterSwingCtx     slc;

   protected final JPanelLayoutable panel;

   protected final LayoutableChainSwing chain;

   public ActionChainAlignmentIterate(LayouterSwingCtx slc, JPanelLayoutable panel, LayoutableChainSwing chain) {
      this.slc = slc;
      this.panel = panel;
      this.chain = chain;

   }

   public void actionPerformed(ActionEvent e) {
      int v = chain.getChainElementAlignment();
      v = (v + 1) % (C.LOGIC_3_BOTTOM_RIGHT + 1);
      chain.setChainElementAlignment(v);
      chain.alignUpdate();
      chain.layoutInvalidate();
      //ask relayout
      panel.doLayout();
   }

}
