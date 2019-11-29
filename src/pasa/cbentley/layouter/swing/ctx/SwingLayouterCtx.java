/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.ctx;

import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;

import pasa.cbentley.byteobjects.src4.ctx.BOCtx;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src5.ctx.C5Ctx;
import pasa.cbentley.layouter.src4.ctx.LayouterCtx;
import pasa.cbentley.layouter.src4.interfaces.ILayoutable;

public class SwingLayouterCtx extends LayouterCtx {

   protected final C5Ctx c5;

   private int           layoutIDGenerator;

   public SwingLayouterCtx(UCtx uc, BOCtx boc, C5Ctx c5) {
      super(uc, boc);
      this.c5 = c5;
   }

   public C5Ctx getC5() {
      return c5;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "SwingLayouterCtx");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "SwingLayouterCtx");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   public void d(JButton c, Dctx dc) {
      dc.root(c, "JButton");
      dc.appendVarWithSpace("text", c.getText());
      this.d((JComponent) c, dc.nLevel());
   }

   public void d(JScrollPane c, Dctx dc) {
      dc.root(c, "JScrollPane");

      JViewport viewport = c.getViewport();
      Component view = viewport.getView();

      //we want a quick view of what
      dc.appendVarWithSpace("of ", view.getClass().getSimpleName() + " named " + view.getName());

      this.d((JComponent) c, dc.nLevel());
      this.d((JViewport) viewport, dc.nLevel());
   }

   public void d(JViewport c, Dctx dc) {
      dc.root(c, "JViewport");
      this.d((JComponent) c, dc.nLevel());

      Rectangle viewRect = c.getViewRect();
      dc.append("ViewRect");
      dc.appendVarWithSpace("x", viewRect.getX());
      dc.appendVarWithSpace("y", viewRect.getY());
      dc.appendVarWithSpace("w", viewRect.getWidth());
      dc.appendVarWithSpace("h", viewRect.getHeight());

      Component view = c.getView();
      dFind(view, dc);
   }

   public void d(JTextPane c, Dctx dc) {
      dc.root(c, "JTextPane");
      this.d((JComponent) c, dc.nLevel());
   }

   public void d(JScrollBar c, Dctx dc) {
      dc.root(c, "JTextPane");
      this.d((JComponent) c, dc.nLevel());
   }

   public void dFind(Component c, Dctx dc) {
      if (c instanceof JComponent) {
         dFind((JComponent) c, dc);
      } else if (c instanceof Container) {
         d((Container) c, dc);
      } else {
         d((Component) c, dc.nLevel());
      }
   }

   /**
    * 
    * @param c
    * @param dc
    */
   public void dFind(JComponent c, Dctx dc) {
      if (c instanceof JButton) {
         d((JButton) c, dc);
      } else if (c instanceof JTextPane) {
         d((JTextPane) c, dc);
      } else if (c instanceof JScrollPane) {
         d((JScrollPane) c, dc);
      } else if (c instanceof JScrollBar) {
         d((JScrollBar) c, dc);
      } else if (c instanceof JViewport) {
         d((JViewport) c, dc);
      } else if (c instanceof JRootPane) {
         d((JRootPane) c, dc);
      } else if (c instanceof JPanel) {
         d((JPanel) c, dc);
      } else if (c instanceof JViewport) {
         d((JViewport) c, dc);
      } else {
         dc.append("Could not find the debug method for " + c.getClass().getName());
         d((JComponent) c, dc.nLevel());
      }
   }

   public void d(JRootPane c, Dctx dc) {
      dc.root(c, "JRootPane");
      dc.appendVarWithSpace("OptimizedDrawingEnabled", c.isOptimizedDrawingEnabled());
      this.d((JComponent) c, dc.nLevel());
   }

   public void d(JLayeredPane c, Dctx dc) {
      dc.root(c, "JLayeredPane");
      dc.appendVarWithSpace("lowestLayer", c.lowestLayer());
      dc.appendVarWithSpace("highestLayer", c.highestLayer());
      this.d((JComponent) c, dc.nLevel());
   }

   public void d(JComponent c, Dctx dc) {
      dc.root(c, "JComponent");
      dc.appendVarWithSpace("x", c.getX());
      dc.appendVarWithSpace("y", c.getY());
      dc.appendVarWithSpace("w", c.getWidth());
      dc.appendVarWithSpace("h", c.getHeight());
      dc.appendVarWithSpace("pw", c.getPreferredSize().getWidth());
      dc.appendVarWithSpace("ph", c.getPreferredSize().getHeight());
      this.d((Container) c, dc.nLevel());
   }

   public void d1(JComponent c, Dctx dc) {
      dc.root(c, "JComponent");
      dc.appendVarWithSpace("x", c.getX());
      dc.appendVarWithSpace("y", c.getY());
      dc.appendVarWithSpace("w", c.getWidth());
      dc.appendVarWithSpace("h", c.getHeight());
      dc.appendVarWithSpace("pw", c.getPreferredSize().getWidth());
      dc.appendVarWithSpace("ph", c.getPreferredSize().getHeight());
      dc.appendVarWithSpace("ComponentCount", c.getComponentCount());
   }

   public void d(Component c, Dctx dc) {
      dc.root(c, "Component");
      dc.appendVarWithSpace("Name", c.getName());
      dc.appendVarWithSpace("isVisible", c.isVisible());
      dc.appendVarWithSpace("isEnabled", c.isEnabled());
      dc.appendVarWithSpace("isFocusable", c.isFocusable());
      dc.appendVarWithSpace("isDisplayable", c.isDisplayable());
      dc.appendVarWithSpace("isOpaque", c.isOpaque());
      dc.appendVarWithSpace("isShowing", c.isShowing());
      dc.appendVarWithSpace("isCursorSet", c.isCursorSet());
   }

   public void d(JPanel panel, Dctx dc) {
      dc.root(panel, "JPanel");
      this.d((JComponent) panel, dc.nLevel());
   }

   public void d(Container c, Dctx dc) {
      dc.root(c, "Container");
      int numComps = c.getComponentCount();
      dc.appendVarWithSpace("ComponentCount", numComps);
      this.d((Component) c, dc.nLevel());
      for (int i = 0; i < numComps; i++) {
         Component child = c.getComponent(i);
         if (child instanceof ILayoutable) {
            dc.nlLvl((ILayoutable) child);
         } else if (child instanceof JComponent) {
            this.dFind((JComponent) child, dc.nLevelTab());
         }
      }
   }

   public void d(JFrame c, Dctx dc) {
      dc.root(c, "JFrame");
      d((Frame) c, dc.nLevel());
   }

   public void d(Frame c, Dctx dc) {
      dc.root(c, "Frame");
      dc.appendVarWithSpace("Title", c.getTitle());
      dc.appendVarWithSpace("getExtendedState", c.getExtendedState());
      d((Window) c, dc.nLevel());
   }

   public void d(Window c, Dctx dc) {
      dc.root(c, "Window");
      dc.appendVarWithSpace("isActive", c.isActive());
      dc.appendVarWithSpace("isAutoRequestFocus", c.isAutoRequestFocus());
      dc.appendVarWithSpace("isCursorSet", c.isCursorSet());
      dc.appendVarWithSpace("isAlwaysOnTop", c.isAlwaysOnTop());
      d((Container) c, dc.nLevel());
   }

   public int getNewLayoutID() {
      layoutIDGenerator++;
      return layoutIDGenerator;
   }

   //#enddebug

}
