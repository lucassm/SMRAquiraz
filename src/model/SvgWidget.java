

package model;


import java.awt.Graphics2D;

import java.awt.Rectangle;

import java.io.IOException;

import org.apache.batik.bridge.BridgeContext;

import org.apache.batik.bridge.DocumentLoader;

import org.apache.batik.bridge.GVTBuilder;

import org.apache.batik.bridge.UserAgent;

import org.apache.batik.bridge.UserAgentAdapter;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;

import org.apache.batik.gvt.GraphicsNode;

import org.apache.batik.util.XMLResourceDescriptor;

import org.netbeans.api.visual.widget.Scene;

import org.netbeans.api.visual.widget.Widget;
import org.w3c.dom.svg.SVGDocument;




public class SvgWidget extends Widget {
    
    
    private GraphicsNode rootGN;
 
    
    
    public SvgWidget(Scene scene, String uri) throws IOException {
        
        super(scene);
        
        setSvgUri(uri);
    }
 
    
    
    public SvgWidget(Scene scene) {
        
        super(scene);
    
    }
 
    
    
    public void setSvgUri(String uri) throws IOException {
        
        
        SAXSVGDocumentFactory df = new SAXSVGDocumentFactory( XMLResourceDescriptor.getXMLParserClassName() );

        SVGDocument doc = df.createSVGDocument(uri);
        
        UserAgent userAgent = new UserAgentAdapter();
        
        DocumentLoader loader = new DocumentLoader(userAgent);
        
        BridgeContext ctx = new BridgeContext(userAgent, loader);
        
        ctx.setDynamicState(BridgeContext.DYNAMIC);
        
        GVTBuilder builder = new GVTBuilder();
        
        rootGN = builder.build(ctx, doc);
        
        revalidate();
    
    }
    
  
    @Override
    
    protected Rectangle calculateClientArea() {
        
        return rootGN.getBounds().getBounds();
    
    }
    
 
    
    @Override
    
    protected void paintWidget() {

        if (null != rootGN) {
            
            Graphics2D g = getGraphics();

            rootGN.paint(g);

        }

    }

}