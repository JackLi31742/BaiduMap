<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
  <head>
    <title>Earthquakes in KML</title>
   <!--  <link rel="stylesheet" href="https://openlayers.org/en/v4.0.1/css/ol.css" type="text/css"> -->
    <!-- The line below is only needed for old environments like Internet Explorer and Android 4.x -->
    <script src="https://cdn.polyfill.io/v2/polyfill.min.js?features=requestAnimationFrame,Element.prototype.classList,URL"></script>
    <!-- <script src="https://openlayers.org/en/v4.0.1/build/ol.js"></script> -->
    <script src="https://code.jquery.com/jquery-2.2.3.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/openlayers/ol.css" />
    <style type="text/css">
        body, #mainMap {
            border: 0px;
            margin: 0px;
            padding: 0px;
            width: 100%;
            height: 100%;
            font-size: 13px;
        }
    </style>
    <script type="text/javascript" src="${ctx}/openlayers/ol.js"></script>
    <style>
      #map {
        position: relative;
      }
      #info {
        position: absolute;
        height: 1px;
        width: 1px;
        z-index: 100;
      }
      .tooltip.in {
        opacity: 1;
      }
      .tooltip.top .tooltip-arrow {
        border-top-color: white;
      }
      .tooltip-inner {
        border: 2px solid white;
      }
    </style>
  </head>
  <body>
    <div id="map" class="map"><div id="info"></div></div>
    <script>
      var styleCache = {};
      var styleFunction = function(feature) {
        // 2012_Earthquakes_Mag5.kml stores the magnitude of each earthquake in a
        // standards-violating <magnitude> tag in each Placemark.  We extract it from
        // the Placemark's name instead.
        var name = feature.get('name');
        var magnitude = parseFloat(name.substr(2));
        var radius = 5 + 20 * (magnitude - 5);
        var style = styleCache[radius];
        if (!style) {
          style = new ol.style.Style({
            image: new ol.style.Circle({
              radius: radius,
              fill: new ol.style.Fill({
                color: 'rgba(255, 153, 0, 0.4)'
              }),
              stroke: new ol.style.Stroke({
                color: 'rgba(255, 204, 0, 0.2)',
                width: 1
              })
            })
          });
          styleCache[radius] = style;
        }
        return style;
      };
 
      var vector = new ol.layer.Vector({
        source: new ol.source.Vector({
          url: '2012_Earthquakes_Mag5.kml',
          format: new ol.format.KML({
            extractStyles: false
          })
        }),
        style: styleFunction
      });
 
      var raster = new ol.layer.Tile({
        source: new ol.source.Stamen({
          layer: 'toner'
        })
      });
 
      var map = new ol.Map({
        layers: [raster, vector],
        target: 'map',
        view: new ol.View({
          center: [0, 0],
          zoom: 2
        })
      });
 
      var info = $('#info');
      info.tooltip({
        animation: false,
        trigger: 'manual'
      });
 
      var displayFeatureInfo = function(pixel) {
        info.css({
          left: pixel[0] + 'px',
          top: (pixel[1] - 15) + 'px'
        });
        var feature = map.forEachFeatureAtPixel(pixel, function(feature) {
          return feature;
        });
        if (feature) {
          info.tooltip('hide')
              .attr('data-original-title', feature.get('name'))
              .tooltip('fixTitle')
              .tooltip('show');
        } else {
          info.tooltip('hide');
        }
      };
 
      map.on('pointermove', function(evt) {
        if (evt.dragging) {
          info.tooltip('hide');
          return;
        }
        displayFeatureInfo(map.getEventPixel(evt.originalEvent));
      });
 
      map.on('click', function(evt) {
        displayFeatureInfo(evt.pixel);
      });
    </script>
  </body>
</html>