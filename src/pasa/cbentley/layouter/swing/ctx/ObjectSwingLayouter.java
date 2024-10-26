package pasa.cbentley.layouter.swing.ctx;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;

public class ObjectSwingLayouter implements IStringable {

   protected final LayouterSwingCtx slc;

   public ObjectSwingLayouter(LayouterSwingCtx slc) {
      this.slc = slc;
      
   }
   
   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, ObjectSwingLayouter.class, "@line5");
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, ObjectSwingLayouter.class);
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return slc.getUC();
   }

   //#enddebug
   

}
