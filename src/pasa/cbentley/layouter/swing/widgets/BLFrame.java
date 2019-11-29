package pasa.cbentley.layouter.swing.widgets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.layouter.swing.ctx.SwingLayouterCtx;

public class BLFrame extends JFrame implements IStringable {

   private KeyListenerRoot          keyLis;

   private BLPanel                  panel;

   protected final SwingLayouterCtx slc;

   public BLFrame(SwingLayouterCtx slc) {
      this.slc = slc;
      panel = new BLPanel(slc);
      panel.setName("rootPanel");
      panel.setBackground(Color.black);
      this.getContentPane().add(panel);

      keyLis = new KeyListenerRoot(slc, panel);
      this.addKeyListener(keyLis);
   }

   public KeyListenerRoot getKeyListenerRoot() {
      return keyLis;
   }

   public BLPanel getBLPanel() {
      return panel;
   }

   public void showDefClosePackLocationNull() {
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
      this.pack();
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      int w = 940;
      int h = 600;
      int dx = screenSize.width / 2 - w / 2;
      int x = dx;
      int dy = screenSize.height / 2 - h / 2;
      int y = dy;
      this.setLocation(x, y);
      this.setSize(w, h);
      this.setVisible(true);
   }

   
   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "BLFrame");
      toStringPrivate(dc);
      slc.d((JFrame)this, dc.nLevel());
      
      
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "BLFrame");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return slc.getUCtx();
   }

   //#enddebug
   

}
