/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.engine;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.layouter.swing.ctx.LayouterSwingCtx;
import pasa.cbentley.swing.window.CBentleyFrame;

/**
 * A Frame with a {@link JPanelLayoutable} and some default commands
 * 
 * <li> {@link KeyListenerF4DebugStringable} on the frame.
 * 
 * @author Charles Bentley
 *
 */
public class JFrameLayouterDemo extends JFrameLayoutable implements IStringable {

   /**
    * 
    */
   private static final long            serialVersionUID = 7165930717051481714L;

   private KeyListenerF4DebugStringable keyLis;

   public JFrameLayouterDemo(LayouterSwingCtx slc) {
      super(slc);

      keyLis = new KeyListenerF4DebugStringable(slc, panel);
      this.addKeyListener(keyLis);
   }

   public KeyListenerF4DebugStringable getKeyListenerRoot() {
      return keyLis;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, JFrameLayouterDemo.class, "@line100");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, JFrameLayouterDemo.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   //#enddebug

}
