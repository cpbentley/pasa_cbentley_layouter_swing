/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.engine;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.layouter.swing.ctx.SwingLayouterCtx;
import pasa.cbentley.swing.window.CBentleyFrame;

/**
 * A Frame with a {@link JPanelLayoutable} and some default commands
 * 
 * <li> {@link KeyListenerF4DebugStringable} on the frame.
 * 
 * @author Charles Bentley
 *
 */
public class JFrameLayoutable extends CBentleyFrame implements IStringable {

   /**
    * 
    */
   private static final long        serialVersionUID = -5618533394543364674L;

   protected JPanelLayoutable       panel;

   protected final SwingLayouterCtx slc;

   public JFrameLayoutable(SwingLayouterCtx slc) {
      super(slc.getSwingCtx());
      this.slc = slc;
      panel = new JPanelLayoutable(slc);
      panel.setName("rootPanel");
      this.getContentPane().add(panel);

   }

   public JPanelLayoutable getPanelLayouter() {
      return panel;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, JFrameLayoutable.class, "@line46");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, JFrameLayoutable.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug

}
