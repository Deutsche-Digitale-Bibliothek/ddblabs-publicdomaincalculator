<?php
/**
 * @file
 * Displays the page which presents the result of a public-domain calculation.
 *
 * Available variables:
 * - $public_domain_status: One of "public_domain", "not_public_domain" and
 *   "unknown" (calculation could not be completed due to missing metadata).
 * - $public_domain_text: German equivalent of the public-domain status
 *   (e.g. "Gemeinfrei").
 * - $pdc_result: An array containing several bits of result information.
 *   - $pdc_result->title: The checked item's title.
 *   - $pdc_result->subtitle: The checked item's subtitle.
 *   - $pdc_result->imageUrl: Thumbnail image URL for the checked item.
 *   - $pdc_result->institution: The name of the institution which archived
 *     the item.
 *   - $pdc_result->trace: The trace of all considered legal questions and
 *     how they were answered by the public domain calculator.
 */
  global $base_path;
  $icon_path = $base_path . $directory . '/images/icons/';
?>

<?php
  drupal_get_messages();
  drupal_set_message($error_message, 'error');
?>

<div class="ddb-pdc-item ddb-pdc-result <?php print $public_domain_status; ?>" >
  <div class="ddb-pdc-item-image">
    <?php if ($pdc_result->imageUrl): ?>
      <img src="<?php print $pdc_result->imageUrl; ?>"/>
    <?php endif; ?>
  </div>

  <div class="ddb-pdc-item-summary">
    <div class="ddb-pdc-item-title">
      <a href="<?php print $pdc_result->itemUrl ?>">
        <?php print htmlspecialchars($pdc_result->title); ?>
      </a>
    </div>
    <div class="ddb-pdc-item-subtitle">
      <?php print htmlspecialchars($pdc_result->subtitle); ?>
    </div>
    <div class="ddb-pdc-item-subtitle">
      <b>Institution:</b>
      <?php print htmlspecialchars($pdc_result->institution); ?>
    </div>
  </div>

  <div class="ddb-pdc-item-license">
    <div class="ddb-pdc-item-license-image">
      <img src="<?php print $icon_path . 'status_' . $public_domain_status . ".png"; ?>"">
    </div>
    <div class="ddb-pdc-item-license-text">
      <?php print $public_domain_text; ?>
    </div>
  </div>
</div>

<div class="ddb-pdc-trace <?php print $public_domain_status ?>">
  <?php foreach ($pdc_result->trace as $trace_item): ?>
    <?php
      print theme('ddb_pdc_trace_item', array('trace_item' => $trace_item));
    ?>
  <?php endforeach; ?>
</div>

<?php if ($general_assumptions): ?>
  <div class="ddb-pdc-general-assumptions">
    <div id="accordion" class="ddb-pdc-general-assumptions">
      <h3 class="accordion-title">
        <a href="#">Grundannahmen</a>
      </h3>

      <div class="accordion-content">
        <?php foreach ($general_assumptions as $question): ?>
          <?php print theme('ddb_pdc_trace_item', array('trace_item' => $question)); ?>
        <?php endforeach; ?>
      </div>
    </div>
  </div>
<?php endif; ?>
<script>
  jQuery(document).ready(function() {
    jQuery('#accordion').accordion({
      collapsible: true,
      active: false,
      heightStyle: 'content',
      autoHeight: false,
      clearStyle: true
    });
  });
</script>
