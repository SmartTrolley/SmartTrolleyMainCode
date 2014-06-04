package slideshowdata;

import java.util.ArrayList;
import java.util.PriorityQueue;

import audiohandler.AudioHandler;

import javafx.scene.shape.Shape;

import smarttrolleygui.Slide;
import texthandler.SlideText;
import texthandler.SlideTextBody;
import videohandler.SlideVideo;
import graphicshandler.ShapePoint;
import graphicshandler.SlideShapeFactory;
import imagehandler.SlideImage;

public class SlideDataImporter {
	SlideShowData data;
	ArrayList<Slide> mediaSlideList;
	
	public SlideDataImporter(SlideShowData data){
		this.data = data;
		
		mediaSlideList = importSlides();
		
		
	}

	private ArrayList<Slide> importSlides() {
		ArrayList<Slide> slides = new ArrayList<Slide>();
		
		for(SlideData slide : data.getSlides()){
			slides.add(importSlide(slide));
		}
		
		return slides;
	}

	private Slide importSlide(SlideData slideData) {
		Slide slide;
		ArrayList<SlideImage> images = importImages(slideData.getImages());
		ArrayList<SlideVideo> videos = importVideos(slideData.getVideos());
		ArrayList<Shape> shapes= importShapes(slideData.getShapes());
		ArrayList<SlideText> texts = importTexts(slideData.getTexts());
		ArrayList<AudioHandler> audios = importAudios(slideData.getAudios());
		
		
		slide = new Slide(1,1,shapes, images, audios, texts, videos, slideData.getDuration());
		
		slide.setStyle("background-color:"+ data.getDefaults().getBackgroundcolor());
		
		return slide;
	}

	private ArrayList<AudioHandler> importAudios(ArrayList<AudioData> audioDatas) {
		
		int defaultVolume = 1;
		ArrayList<AudioHandler> audios = new ArrayList<AudioHandler>();
		
		for(AudioData audioData : audioDatas){
			audios.add(new AudioHandler(audioData.getUrlname(),audioData.getStarttime(),defaultVolume));
		}
		
		return audios;
	}

	private ArrayList<SlideText> importTexts(ArrayList<TextData> textDatas ) {
		
		ArrayList<SlideText> texts = new ArrayList<SlideText>();
		ArrayList<SlideTextBody> bodies = new ArrayList<SlideTextBody>();
		for(TextData textData : textDatas){
			
			bodies = importTextBodies(textData.getTextbodies());
			texts. add(new SlideText(
										bodies, 
										textData.getFont(), 
										textData.getFontcolor(), 
										textData.getFontsize(), 
										textData.getXstart(), textData.getXstart(), 
										textData.getXend(), 
										textData.getYend(), 
										textData.getStarttime(), 
										textData.getDuration())
			);
		}
		
		return texts;
	}

	private ArrayList<SlideTextBody> importTextBodies(ArrayList<TextBodyData> bodyDatas) {
		ArrayList<SlideTextBody> bodies = new ArrayList<SlideTextBody>();
		
		for(TextBodyData body : bodyDatas){
			
			bodies.add(new SlideTextBody(body.getTextstring(), body.getBold(), body.getItalic(), body.getUnderlined()));
			
		}
		return bodies;
	}

	private ArrayList<Shape> importShapes(ArrayList<ShapeData> shapeDatas) {
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		SlideShapeFactory shapeFactory;
		PriorityQueue<ShapePoint> points;
		
		for(ShapeData shapeData : shapeDatas){
			points = importPoints(shapeData.getPoints());
			
			shapeFactory = new SlideShapeFactory(
													points,
													shapeData.getWidth(), 
													shapeData.getHeight(), 
													shapeData.getFillcolor(), 
													shapeData.getLinecolor(), 
													shapeData.getStarttime(), 
													shapeData.getDuration());
			shapes.add(shapeFactory.getShape());
		}
		
		return shapes;
	}

	private PriorityQueue<ShapePoint> importPoints(ArrayList<PointData> pointDatas) {
		PriorityQueue<ShapePoint> points = new PriorityQueue<ShapePoint>();
		
		for(PointData pointData: pointDatas){
			points.add(new ShapePoint(pointData.getX(), pointData.getY(), pointData.getNum()));
		}
		
		return points;
	}

	private ArrayList<SlideVideo> importVideos(ArrayList<VideoData> videoDatas) {
		ArrayList<SlideVideo> videos = new ArrayList<SlideVideo>();
		
		for(VideoData videoData : videoDatas){
			
			videos.add(new SlideVideo(videoData.getUrlname(),
									  videoData.getXstart(), 
									  videoData.getYstart(), 
									  videoData.getWidth(), 
									  videoData.getHeight(), 
									  videoData.getLoop(), 
									  videoData.getStarttime(),
									  videoData.getDuration()));
		}
		
		return videos;
	}

	private ArrayList<SlideImage> importImages(ArrayList<ImageData> imageDatas) {
		ArrayList<SlideImage> audios = new ArrayList<SlideImage>();
		
		for(ImageData imageData : imageDatas){
			
			audios.add(new SlideImage(imageData.getUrlname(),
									  imageData.getXstart(), 
									  imageData.getYstart(), 
									  imageData.getWidth(), 
									  imageData.getHeight(), 
									  imageData.getStarttime(),
									  imageData.getDuration()));
		}
		
		return audios;
	}

	public ArrayList<Slide> getSlides() {
		return mediaSlideList;
	}
}
